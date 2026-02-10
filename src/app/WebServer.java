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
import java.util.HashMap;
import java.util.Map;

/**
 * Minimal HTTP server exposing command execution for a web UI.
 */
public class WebServer {
    private static final String DEFAULT_EDGE_ALGORITHM = "generateEdgesM2";
    private static final String DEFAULT_CORNER_ALGORITHM = "generateCornersPochmann";

    private final CommandService commandService;
    private final CubeManager cubeManager;
    private final CubeNetRenderer cubeNetRenderer;

    public WebServer(CommandService commandService, CubeManager cubeManager) {
        this.commandService = commandService;
        this.cubeManager = cubeManager;
        this.cubeNetRenderer = new CubeNetRenderer();
    }

    public void start(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", this::handleIndex);
        server.createContext("/execute", this::handleExecute);
        server.start();
    }

    private void handleIndex(HttpExchange exchange) throws IOException {
        CubeState cubeState = new CubeState(cubeManager.getCube());
        sendHtml(exchange, renderPage(new PageModel(
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

    private void handleExecute(HttpExchange exchange) throws IOException {
        Map<String, String> params = parseQuery(exchange.getRequestURI().getRawQuery());
        String action = params.getOrDefault("action", "");
        String scrambleText = params.getOrDefault("scramble", "").trim();
        String edgeAlgorithm = params.getOrDefault("edgeAlgorithm", DEFAULT_EDGE_ALGORITHM);
        String cornerAlgorithm = params.getOrDefault("cornerAlgorithm", DEFAULT_CORNER_ALGORITHM);
        boolean memoryHelperEnabledInForm = "on".equals(params.get("memoryHelper"));

        String errorOutput = "";
        String edgeOutput = "";
        String cornerOutput = "";

        if (cubeManager.isMemoryHelperEnabled() != memoryHelperEnabledInForm) {
            Result toggleResult = executeCommand("toggleMemoryHelper", "");
            if (!toggleResult.success()) {
                errorOutput = toggleResult.errorMessage();
            }
        }

        if (errorOutput.isEmpty()) {
            if ("generateScramble".equals(action)) {
                Result scrambleResult = executeCommand("generateScramble", "");
                if (scrambleResult.success()) {
                    scrambleText = scrambleResult.scramble();
                } else {
                    errorOutput = scrambleResult.errorMessage();
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
                            Result cornerResult = executeCommand(cornerAlgorithm, "");
                            if (!cornerResult.success()) {
                                errorOutput = cornerResult.errorMessage();
                            } else {
                                cornerOutput = cornerResult.cornerAlgorithmOutput();
                            }
                        }
                    }
                }
            }
        }

        CubeState cubeState = new CubeState(cubeManager.getCube());
        sendHtml(exchange, renderPage(new PageModel(
                errorOutput,
                edgeOutput,
                cornerOutput,
                edgeAlgorithm,
                cornerAlgorithm,
                cubeManager.isMemoryHelperEnabled(),
                cubeNetRenderer.render(cubeState),
                scrambleText
        )));
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

    private String renderPage(PageModel pageModel) {
        String edgeAlgorithmOptions = buildEdgeAlgorithmOptions(pageModel.edgeAlgorithm());
        String cornerAlgorithmOptions = buildCornerAlgorithmOptions(pageModel.cornerAlgorithm());

        return """
                <!doctype html>
                <html lang="de">
                  <head>
                    <meta charset="utf-8" />
                    <title>Blindfolded Solution Generator</title>
                    <style>
                      body { font-family: Arial, sans-serif; margin: 2rem; background: #f8fafc; color: #111827; }
                      h1 { margin-bottom: 1.5rem; }
                      .panel { background: white; border: 1px solid #e5e7eb; border-radius: 10px; padding: 1rem; margin-bottom: 1rem; }
                      .controls { display: grid; grid-template-columns: repeat(2, minmax(0, 1fr)); gap: 1rem; }
                      label { display: block; margin-top: 0.25rem; font-weight: bold; }
                      textarea, select, button { width: 100%%; margin-top: 0.5rem; }
                      #scramble { min-height: 2.6rem; max-height: 14rem; resize: none; overflow-y: hidden; }
                      .readonly { background: #f3f4f6; min-height: 9rem; }
                      .button-row { display: grid; grid-template-columns: 1fr; gap: 0.75rem; margin-top: 1rem; }
                      .switch-row { margin-top: 1rem; display: flex; align-items: center; gap: 0.5rem; }
                      .cube-net {
                        display: grid;
                        grid-template-columns: repeat(4, auto);
                        grid-template-rows: repeat(3, auto);
                        gap: 12px;
                        margin-top: 1rem;
                      }
                      .face {
                        display: grid;
                        grid-template-columns: repeat(3, 24px);
                        grid-template-rows: repeat(3, 24px);
                        gap: 2px;
                        padding: 6px;
                        background: #f2f2f2;
                        border-radius: 6px;
                        border: 1px solid #ddd;
                      }
                      .sticker {
                        width: 24px;
                        height: 24px;
                        border: 1px solid #444;
                        box-sizing: border-box;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                      }
                      .sticker-label {
                        font-size: 10px;
                        font-weight: bold;
                        color: #111;
                        text-shadow: 0 0 2px rgba(255, 255, 255, 0.6);
                      }
                      .face-up { grid-column: 2; grid-row: 1; }
                      .face-left { grid-column: 1; grid-row: 2; }
                      .face-front { grid-column: 2; grid-row: 2; }
                      .face-right { grid-column: 3; grid-row: 2; }
                      .face-back { grid-column: 4; grid-row: 2; }
                      .face-down { grid-column: 2; grid-row: 3; }
                    </style>
                  </head>
                  <body>
                    <h1>Blindfolded Trainer Dashboard</h1>
                    <form action="/execute" method="get">
                      <div class="panel controls">
                        <div>
                          <label for="edgeAlgorithm">Edge Algorithmus</label>
                          <select id="edgeAlgorithm" name="edgeAlgorithm">
                %s
                          </select>
                        </div>
                        <div>
                          <label for="cornerAlgorithm">Corner Algorithmus</label>
                          <select id="cornerAlgorithm" name="cornerAlgorithm">
                %s
                          </select>
                        </div>
                        <div style="grid-column: 1 / -1;">
                          <label for="scramble">Scramble</label>
                          <textarea id="scramble" name="scramble">%s</textarea>
                          <button type="submit" name="action" value="generateScramble">GenerateScramble</button>
                        </div>
                      </div>
                
                      <div class="panel">
                        <div class="switch-row">
                          <input id="memoryHelper" type="checkbox" name="memoryHelper" %s />
                          <label for="memoryHelper" style="margin: 0;">Memory Helper</label>
                        </div>
                        <div class="button-row">
                          <button type="submit" name="action" value="scrambleCube">ScrambleCube + Algos</button>
                        </div>
                      </div>
                
                      <div class="panel">
                        <textarea id="message" class="readonly" readonly>%s</textarea>
                        <label>Cube Snapshot</label>
                        %s
                      </div>
                    </form>
                    <script>
                      const scrambleInput = document.getElementById('scramble');
                      const autoResize = () => {
                        scrambleInput.style.height = 'auto';
                        scrambleInput.style.height = `${scrambleInput.scrollHeight}px`;
                      };
                      scrambleInput.addEventListener('input', autoResize);
                      autoResize();
                    </script>
                  </body>
                </html>
                """.formatted(
                edgeAlgorithmOptions,
                cornerAlgorithmOptions,
                escapeHtml(pageModel.scramble()),
                pageModel.memoryHelperEnabled() ? "checked" : "",
                escapeHtml(buildOutput(pageModel.errorOutput(), pageModel.edgeOutput(), pageModel.cornerOutput())),
                pageModel.cubeNetHtml()
        );
    }

    private String buildOutput(String errorOutput, String edgeOutput, String cornerOutput) {
        if (!errorOutput.isBlank()) {
            return "ERROR: " + errorOutput;
        }

        String trimmedEdge = edgeOutput.trim();
        String trimmedCorner = cornerOutput.trim();
        if (trimmedEdge.isBlank() && trimmedCorner.isBlank()) {
            return "";
        }

        return "Edge Pairs\n" + trimmedEdge + "\n\nCorner Pairs\n" + trimmedCorner;
    }

    private String buildEdgeAlgorithmOptions(String selectedEdgeAlgorithm) {
        return buildOption("generateEdgesM2", selectedEdgeAlgorithm, "generateEdgesM2") +
                buildOption("generateEdgesPochmann", selectedEdgeAlgorithm, "generateEdgesPochmann");
    }

    private String buildCornerAlgorithmOptions(String selectedCornerAlgorithm) {
        return buildOption("generateCornersPochmann", selectedCornerAlgorithm, "generateCornersPochmann");
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

    private static void sendHtml(HttpExchange exchange, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream output = exchange.getResponseBody()) {
            output.write(bytes);
        }
    }

    private record PageModel(
            String errorOutput,
            String edgeOutput,
            String cornerOutput,
            String edgeAlgorithm,
            String cornerAlgorithm,
            boolean memoryHelperEnabled,
            String cubeNetHtml,
            String scramble
    ) {
    }
}