package app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
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
    private final CommandService commandService;
    private final CommandRegistry registry;

    public WebServer(CommandService commandService, CommandRegistry registry) {
        this.commandService = commandService;
        this.registry = registry;
    }

    public void start(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", this::handleIndex);
        server.createContext("/execute", this::handleExecute);
        server.start();
    }

    private void handleIndex(HttpExchange exchange) throws IOException {
        StringBuilder options = new StringBuilder();
        for (String command : registry.listNames()) {
            options.append("<option value=\"")
                .append(command)
                .append("\">")
                .append(command)
                .append("</option>");
        }

        String body = """
            <!doctype html>
            <html lang="de">
              <head>
                <meta charset="utf-8" />
                <title>Blindfolded Solution Generator</title>
              </head>
              <body>
                <h1>Command Tester</h1>
                <form action="/execute" method="get">
                  <label for="name">Command</label>
                  <select id="name" name="name">
            """ + options + """
                  </select>
                  <button type="submit">Execute</button>
                </form>
              </body>
            </html>
            """;

        sendHtml(exchange, body);
    }

    private void handleExecute(HttpExchange exchange) throws IOException {
        Map<String, String> params = parseQuery(exchange.getRequestURI().getRawQuery());
        String name = params.get("name");
        Result result = commandService.execute(new CommandRequest(name, null));

        String message = result.success()
            ? "OK: " + result.message()
            : "ERROR: " + result.message();

        String body = """
            <!doctype html>
            <html lang="de">
              <head>
                <meta charset="utf-8" />
                <title>Command Result</title>
              </head>
              <body>
                <p>%s</p>
                <p><a href="/">Zurück</a></p>
              </body>
            </html>
            """.formatted(escapeHtml(message));

        sendHtml(exchange, body);
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

    private static String escapeHtml(String value) {
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
