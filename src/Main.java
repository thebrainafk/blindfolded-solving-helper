import app.CommandRegistry;
import app.CommandRequest;
import app.CommandService;
import app.WebServer;
import model.CubeManager;
import view.Result;

/**
 * asdf.
 *
 * @author uckhu
 */
public final class Main {

    private Main() {
    }

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
