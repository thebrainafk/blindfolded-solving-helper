import app.CommandRegistry;
import app.CommandRequest;
import app.CommandService;
import app.WebServer;
import model.CubeManager;
import view.Result;

public final class Main {

    private Main() {
    }

    /**
     * Starts the web application, prints the startup URL and performs an initial scramble generation.
     *
     * @param args command-line arguments (currently unused)
     */
    public static void main(String[] args) {
        CubeManager cubeManager = new CubeManager();
        CommandRegistry registry = new CommandRegistry(cubeManager);
        CommandService service = new CommandService(registry);

        int port = resolvePort();
        WebServer webServer = new WebServer(service, cubeManager);
        try {
            webServer.start(port);
            System.out.printf("Web server running at http://localhost:%d%n", port);
        } catch (Exception error) {
            System.out.printf("ERROR: %s%n", error.getMessage());
        }
        Result result = service.execute(new CommandRequest("generateScramble", null));
        if (result.success()) {
            System.out.println("Command executed successfully.");
        } else {
            System.out.printf("ERROR: %s%n", result.errorMessage());
        }
    }

    /**
     * Resolves the HTTP port from the {@code PORT} environment variable.
     *
     * @return parsed port number or {@code 8080} if no valid value is configured
     */
    private static int resolvePort() {
        String rawPort = System.getenv("PORT");
        if (rawPort == null || rawPort.isBlank()) {
            return 8080;
        }

        try {
            return Integer.parseInt(rawPort.trim());
        } catch (NumberFormatException error) {
            return 8080;
        }
    }
}
