package app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import model.CubeState;
import view.Result;
import view.CubeStateFormatter;

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
    private final CommandService commandService;
    private final CommandRegistry registry;
    private final CubeStateFormatter cubeStateFormatter;

    public WebServer(CommandService commandService, CommandRegistry registry) {
        this.commandService = commandService;
        this.registry = registry;
        this.cubeStateFormatter = new CubeStateFormatter();
    }

    public void start(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", this::handleIndex);
        server.createContext("/execute", this::handleExecute);
        server.start();
    }

    private void handleIndex(HttpExchange exchange) throws IOException {
        sendHtml(exchange, renderPage(null, "", "", ""));
    }

    private void handleExecute(HttpExchange exchange) throws IOException {
        Map<String, String> params = parseQuery(exchange.getRequestURI().getRawQuery());
        String name = params.get("name");
        String arguments = params.getOrDefault("args", "");
        String[] parsedArguments = parseArguments(arguments);
        Result result = commandService.execute(new CommandRequest(name, parsedArguments));

        String message = result.success()
            ? "OK: " + result.message()
            : "ERROR: " + result.message();

        String cubeStateText = formatCubeState(result.cubeState());

        sendHtml(exchange, renderPage(name, arguments, message, cubeStateText));
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

    private String renderPage(String selectedCommand, String arguments, String message, String cubeStateText) {
        String options = buildCommandOptions(selectedCommand);
        return """
            <!doctype html>
            <html lang="de">
              <head>
                <meta charset="utf-8" />
                <title>Blindfolded Solution Generator</title>
                <style>
                  body { font-family: Arial, sans-serif; margin: 2rem; }
                  label { display: block; margin-top: 1rem; font-weight: bold; }
                  textarea { width: 100%%; min-height: 6rem; }
                  select, input, button { margin-top: 0.5rem; }
                  .outputs { margin-top: 2rem; }
                </style>
              </head>
              <body>
                <h1>Command Tester</h1>
                <form action="/execute" method="get">
                  <label for="name">Command</label>
                  <select id="name" name="name">
            %s
                  </select>
                  <label for="args">Arguments</label>
                  <input id="args" name="args" type="text" value="%s" />
                  <div>
                    <button type="submit">Execute</button>
                  </div>
                </form>
                <div class="outputs">
                  <label for="message">Output</label>
                  <textarea id="message" readonly>%s</textarea>
                  <label for="cubeState">Cube State</label>
                  <textarea id="cubeState" readonly>%s</textarea>
                </div>
              </body>
            </html>
            """.formatted(
            options,
            escapeHtml(arguments),
            escapeHtml(message),
            escapeHtml(cubeStateText)
        );
    }

    private String buildCommandOptions(String selectedCommand) {
        StringBuilder options = new StringBuilder();
        for (String command : registry.listNames()) {
            options.append("<option value=\"")
                .append(command)
                .append("\"");
            if (command.equals(selectedCommand)) {
                options.append(" selected");
            }
            options.append(">")
                .append(command)
                .append("</option>");
        }
        return options.toString();
    }

    private static String[] parseArguments(String arguments) {
        if (arguments == null || arguments.isBlank()) {
            return null;
        }
        return arguments.trim().split("\\s+");
    }

    private String formatCubeState(CubeState cubeState) {
        if (cubeState == null) {
            return "";
        }
        return cubeStateFormatter.format(cubeState);
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
}
