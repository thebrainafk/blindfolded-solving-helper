import app.CommandRegistry;
import app.CommandService;
import app.WebServer;
import model.CubeManager;
import app.CommandRequest;
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

        WebServer webServer = new WebServer(service, registry);
        try {
            webServer.start(8080);
            System.out.println("Web server running at http://localhost:8080");
        } catch (Exception error) {
            System.out.printf("ERROR: %s%n", error.getMessage());
        }
        Result result = service.execute(new CommandRequest("generateScramble", null));
        if (result.success()) {
            System.out.println("Command executed successfully.");
        } else {
            System.out.printf("ERROR: %s%n", result.message());
        }
    }
}
