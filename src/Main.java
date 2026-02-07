import app.CommandRegistry;
import app.CommandRequest;
import app.CommandService;
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

    /**
     * asdf.
     *
     * @param args
     */
    public static void main(String[] args) {
        CubeManager cubeManager = new CubeManager();
        CommandRegistry registry = new CommandRegistry(cubeManager);
        CommandService service = new CommandService(registry);

        Result result = service.execute(new CommandRequest("generateScramble", null));
        if (result.success()) {
            System.out.println("Command executed successfully.");
        } else {
            System.out.printf("ERROR: %s%n", result.message());
        }
    }
}
