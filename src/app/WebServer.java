package app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import model.CubeManager;
import model.CubeState;
import view.CubeNetRenderer;
import view.Result;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Minimal HTTP server exposing command execution for a web UI.
 */
public class WebServer {
    private static final String DEFAULT_EDGE_ALGORITHM = "generateEdgesM2";
    private static final String DEFAULT_CORNER_ALGORITHM = "generateCornersPochmann";
    private static final Path HTML_TEMPLATE_PATH = Path.of("src", "web", "index.html");
    private static final Path CSS_TEMPLATE_PATH = Path.of("src", "web", "styles.css");

    private final CommandService commandService;
    private final CubeManager cubeManager;
    private final CubeNetRenderer cubeNetRenderer;
    private final Set<String> corsAllowedOrigins;

    /**
     * Creates a new web server with command and cube state access.
     *
     * @param commandService command execution service
     * @param cubeManager shared cube manager that stores the current cube state
     */
    public WebServer(CommandService commandService, CubeManager cubeManager) {
        this.commandService = commandService;
        this.cubeManager = cubeManager;
        this.cubeNetRenderer = new CubeNetRenderer();
        this.corsAllowedOrigins = parseAllowedOrigins(System.getenv("CORS_ALLOWED_ORIGINS"));
    }

    /**
     * Starts the HTTP server and registers all web and API endpoints.
     *
     * @param port TCP port to bind
     * @throws IOException if the server socket cannot be opened
     */
    public void start(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", this::handleIndex);
        server.createContext("/execute", this::handleExecute);
        server.createContext("/api/execute", this::handleApiExecute);
        server.createContext("/api/status", this::handleApiStatus);
        server.createContext("/styles.css", this::handleStyles);
        server.start();
    }

    private void handleIndex(HttpExchange exchange) throws IOException {
        CubeState cubeState = new CubeState(cubeManager.getCube());
        sendHtml(exchange, renderPage(new PageModel(
                "",
                "",
                "",
                "",
                "",
                DEFAULT_EDGE_ALGORITHM,
                DEFAULT_CORNER_ALGORITHM,
                cubeManager.isMemoryHelperEnabled(),
                cubeNetRenderer.render(cubeState),
                ""
        )));
    }


    private void handleStyles(HttpExchange exchange) throws IOException {
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendNotFound(exchange);
            return;
        }
        sendCss(exchange, readTemplate(CSS_TEMPLATE_PATH));
    }

    private void handleExecute(HttpExchange exchange) throws IOException {
        Map<String, String> params = parseQuery(exchange.getRequestURI().getRawQuery());
        String action = params.getOrDefault("action", "");
        String scrambleText = params.getOrDefault("scramble", "").trim();
        String edgeAlgorithm = params.getOrDefault("edgeAlgorithm", DEFAULT_EDGE_ALGORITHM);
        String cornerAlgorithm = params.getOrDefault("cornerAlgorithm", DEFAULT_CORNER_ALGORITHM);
        boolean memoryHelperEnabledInForm = "on".equals(params.get("memoryHelper"));

        CommandOutputs outputs = runAction(action, scrambleText, edgeAlgorithm, cornerAlgorithm, memoryHelperEnabledInForm);

        CubeState cubeState = new CubeState(cubeManager.getCube());
        sendHtml(exchange, renderPage(new PageModel(
                outputs.errorOutput(),
                outputs.edgeOutput(),
                outputs.cornerOutput(),
                outputs.edgeSetupMovesOutput(),
                outputs.cornerSetupMovesOutput(),
                edgeAlgorithm,
                cornerAlgorithm,
                cubeManager.isMemoryHelperEnabled(),
                cubeNetRenderer.render(cubeState),
                outputs.scrambleText()
        )));
    }

    private void handleApiExecute(HttpExchange exchange) throws IOException {
        addCorsHeaders(exchange);

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendEmpty(exchange, 204);
            return;
        }

        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendMethodNotAllowed(exchange);
            return;
        }

        Map<String, String> params = parseQuery(exchange.getRequestURI().getRawQuery());
        String action = params.getOrDefault("action", "");
        String scrambleText = params.getOrDefault("scramble", "").trim();
        String edgeAlgorithm = params.getOrDefault("edgeAlgorithm", DEFAULT_EDGE_ALGORITHM);
        String cornerAlgorithm = params.getOrDefault("cornerAlgorithm", DEFAULT_CORNER_ALGORITHM);
        boolean memoryHelperEnabledInForm = "true".equalsIgnoreCase(params.getOrDefault("memoryHelper", "false"));

        CommandOutputs outputs = runAction(action, scrambleText, edgeAlgorithm, cornerAlgorithm, memoryHelperEnabledInForm);
        CubeState cubeState = new CubeState(cubeManager.getCube());
        String json = "{" +
                "\"success\":" + outputs.errorOutput().isBlank() + "," +
                "\"error\":\"" + escapeJson(outputs.errorOutput()) + "\"," +
                "\"scramble\":\"" + escapeJson(outputs.scrambleText()) + "\"," +
                "\"edgePairs\":\"" + escapeJson(outputs.edgeOutput()) + "\"," +
                "\"edgeSetupMoves\":\"" + escapeJson(outputs.edgeSetupMovesOutput()) + "\"," +
                "\"cornerPairs\":\"" + escapeJson(outputs.cornerOutput()) + "\"," +
                "\"cornerSetupMoves\":\"" + escapeJson(outputs.cornerSetupMovesOutput()) + "\"," +
                "\"memoryHelperEnabled\":" + cubeManager.isMemoryHelperEnabled() + "," +
                "\"cubeNetHtml\":\"" + escapeJson(cubeNetRenderer.render(cubeState)) + "\"" +
                "}";
        sendJson(exchange, json);
    }

    private void handleApiStatus(HttpExchange exchange) throws IOException {
        addCorsHeaders(exchange);

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendEmpty(exchange, 204);
            return;
        }

        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendMethodNotAllowed(exchange);
            return;
        }

        sendJson(exchange, "{\"success\":true,\"status\":\"ok\"}");
    }

    private CommandOutputs runAction(
            String action,
            String scrambleText,
            String edgeAlgorithm,
            String cornerAlgorithm,
            boolean memoryHelperEnabledInForm
    ) {
        String errorOutput = "";
        String edgeOutput = "";
        String cornerOutput = "";
        String edgeSetupMovesOutput = "";
        String cornerSetupMovesOutput = "";

        if (cubeManager.isMemoryHelperEnabled() != memoryHelperEnabledInForm) {
            Result toggleResult = executeCommand("toggleMemoryHelper", "");
            if (!toggleResult.success()) {
                errorOutput = toggleResult.errorMessage();
            }
        }

        if (errorOutput.isEmpty()) {
            if ("generateScramble".equals(action)) {
                Result resetResult = executeCommand("resetCube", "");
                if (!resetResult.success()) {
                    errorOutput = resetResult.errorMessage();
                } else {
                    Result scrambleResult = executeCommand("generateScramble", "");
                    if (scrambleResult.success()) {
                        scrambleText = scrambleResult.scramble();
                    } else {
                        errorOutput = scrambleResult.errorMessage();
                    }
                }
            } else if ("scrambleCube".equals(action)) {
                Result resetResult = executeCommand("resetCube", "");
                if (!resetResult.success()) {
                    errorOutput = resetResult.errorMessage();
                } else {
                    Result scrambleResult = executeCommand("scrambleCube", scrambleText);
                    if (!scrambleResult.success()) {
                        errorOutput = scrambleResult.errorMessage();
                    } else {
                        scrambleText = scrambleResult.scramble();
                        Result edgeResult = executeCommand(edgeAlgorithm, "");
                        if (!edgeResult.success()) {
                            errorOutput = edgeResult.errorMessage();
                        } else {
                            edgeOutput = edgeResult.edgeAlgorithmOutput();
                            edgeSetupMovesOutput = edgeResult.edgeSetupMovesOutput();
                            Result cornerResult = executeCommand(cornerAlgorithm, "");
                            if (!cornerResult.success()) {
                                errorOutput = cornerResult.errorMessage();
                            } else {
                                cornerOutput = cornerResult.cornerAlgorithmOutput();
                                cornerSetupMovesOutput = cornerResult.cornerSetupMovesOutput();
                            }
                        }
                    }
                }
            }
        }

        return new CommandOutputs(
                errorOutput,
                edgeOutput,
                cornerOutput,
                edgeSetupMovesOutput,
                cornerSetupMovesOutput,
                scrambleText
        );
    }

    private Result executeCommand(String name, String args) {
        return commandService.execute(new CommandRequest(name, args));
    }

    private static Map<String, String> parseQuery(String query) {
        Map<String, String> params = new HashMap<>();
        if (query == null || query.isBlank()) {
            return params;
        }
        for (String pair : query.split("&")) {
            String[] parts = pair.split("=", 2);
            String key = decode(parts[0]);
            String value = parts.length > 1 ? decode(parts[1]) : "";
            params.put(key, value);
        }
        return params;
    }

    private static String decode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    private String renderPage(PageModel pageModel) throws IOException {
        String edgeAlgorithmOptions = buildEdgeAlgorithmOptions(pageModel.edgeAlgorithm());
        String cornerAlgorithmOptions = buildCornerAlgorithmOptions(pageModel.cornerAlgorithm());

        return readTemplate(HTML_TEMPLATE_PATH).formatted(
                pageModel.cubeNetHtml(),
                edgeAlgorithmOptions,
                cornerAlgorithmOptions,
                escapeHtml(pageModel.scramble()),
                pageModel.memoryHelperEnabled() ? "checked" : "",
                escapeHtml(buildEdgePairsOutput(pageModel.errorOutput(), pageModel.edgeOutput())),
                escapeHtml(buildSetupOutput(pageModel.errorOutput(), pageModel.edgeSetupMovesOutput())),
                escapeHtml(buildCornerPairsOutput(pageModel.errorOutput(), pageModel.cornerOutput())),
                escapeHtml(buildSetupOutput(pageModel.errorOutput(), pageModel.cornerSetupMovesOutput()))
        );
    }

    private static String readTemplate(Path templatePath) throws IOException {
        return Files.readString(templatePath, StandardCharsets.UTF_8);
    }

    private String buildSetupOutput(String errorOutput, String setupMovesOutput) {
        if (!errorOutput.isBlank()) {
            return "ERROR: " + errorOutput;
        }
        return setupMovesOutput.trim();
    }

    private String buildEdgePairsOutput(String errorOutput, String edgeOutput) {
        if (!errorOutput.isBlank()) {
            return "ERROR: " + errorOutput;
        }
        return edgeOutput.trim();
    }

    private String buildCornerPairsOutput(String errorOutput, String cornerOutput) {
        if (!errorOutput.isBlank()) {
            return "ERROR: " + errorOutput;
        }
        return cornerOutput.trim();
    }

    private String buildEdgeAlgorithmOptions(String selectedEdgeAlgorithm) {
        return buildOption("generateEdgesM2", selectedEdgeAlgorithm, "M2 Edges") +
                buildOption("generateEdgesPochmann", selectedEdgeAlgorithm, "Pochmann Edges");
    }

    private String buildCornerAlgorithmOptions(String selectedCornerAlgorithm) {
        return buildOption("generateCornersPochmann", selectedCornerAlgorithm, "Pochmann Corners");
    }

    private String buildOption(String value, String selectedValue, String label) {
        StringBuilder option = new StringBuilder();
        option.append("<option value=\"")
                .append(value)
                .append("\"");
        if (value.equals(selectedValue)) {
            option.append(" selected");
        }
        option.append(">")
                .append(label)
                .append("</option>");
        return option.toString();
    }

    private static String escapeHtml(String value) {
        if (value == null) {
            return "";
        }
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    private static String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    private void addCorsHeaders(HttpExchange exchange) {
        String requestOrigin = exchange.getRequestHeaders().getFirst("Origin");
        if (requestOrigin == null || requestOrigin.isBlank()) {
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        } else if (corsAllowedOrigins.contains("*") || corsAllowedOrigins.contains(requestOrigin)) {
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", requestOrigin);
        }

        exchange.getResponseHeaders().set("Vary", "Origin");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
    }

    private static Set<String> parseAllowedOrigins(String rawOrigins) {
        Set<String> origins = new HashSet<>();
        if (rawOrigins == null || rawOrigins.isBlank()) {
            origins.add("*");
            return origins;
        }

        for (String origin : rawOrigins.split(",")) {
            String trimmedOrigin = origin.trim();
            if (!trimmedOrigin.isBlank()) {
                origins.add(trimmedOrigin);
            }
        }

        if (origins.isEmpty()) {
            origins.add("*");
        }

        return origins;
    }

    private static void sendHtml(HttpExchange exchange, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream output = exchange.getResponseBody()) {
            output.write(bytes);
        }
    }


    private static void sendCss(HttpExchange exchange, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/css; charset=utf-8");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream output = exchange.getResponseBody()) {
            output.write(bytes);
        }
    }

    private static void sendJson(HttpExchange exchange, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream output = exchange.getResponseBody()) {
            output.write(bytes);
        }
    }

    private static void sendEmpty(HttpExchange exchange, int statusCode) throws IOException {
        exchange.sendResponseHeaders(statusCode, -1);
        exchange.close();
    }

    private static void sendMethodNotAllowed(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(405, -1);
        exchange.close();
    }

    private static void sendNotFound(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(404, -1);
        exchange.close();
    }

    private record PageModel(
            String errorOutput,
            String edgeOutput,
            String cornerOutput,
            String edgeSetupMovesOutput,
            String cornerSetupMovesOutput,
            String edgeAlgorithm,
            String cornerAlgorithm,
            boolean memoryHelperEnabled,
            String cubeNetHtml,
            String scramble
    ) {
    }

    private record CommandOutputs(
            String errorOutput,
            String edgeOutput,
            String cornerOutput,
            String edgeSetupMovesOutput,
            String cornerSetupMovesOutput,
            String scrambleText
    ) {
    }
}
