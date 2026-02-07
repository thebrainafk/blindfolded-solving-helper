import app.CommandRegistry;
import app.CommandService;
import app.WebServer;
import model.CubeManager;

/**
 * asdf.
 *
 * @author uckhu
 */
public final class Main {

    private Main() {
    }

    /**
     * asdf.
     *
     * @param args
     */
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
    }
}
