package view.commands;

import model.CubeManager;
import view.ParsedInput;
import view.Result;
import view.UI;

import java.util.HashMap;
import java.util.Map;

/**
 * asdf.
 *
 * @author Programmieren-Team
 * @author uckhu
 */
public class CommandHandler {
    private static final String QUIT_COMMAND = "quit";

    private final CubeManager cubeManager;
    private final UI ui;

    private final Map<String, Command> commands;

    /**
     * asdf.
     */
    public CommandHandler(UI ui) {
        this.cubeManager = new CubeManager();
        this.ui = ui;
        this.commands = new HashMap<>();
        this.initCommands();
    }

    private void initCommands() {
        addCommand(new GenerateScramble(cubeManager));
    }

    private void addCommand(Command command) {
        this.commands.put(command.getCommandName(), command);
    }

    /**
     * asdf.
     */
    public Result runInstance(ParsedInput input) {
        if (input == null) {
            return Result.error("Invalid input");
        }

        String commandString = input.command();
        String[] arguments = input.arguments();

        if (commandString.equals(QUIT_COMMAND) && arguments == null) {
            ui.quit();
        } else if (commandString.equals(QUIT_COMMAND)) {
            return Result.error("No parameters are expected");
        }

        Command command = commands.get(commandString);
        if (command == null) {
            return Result.error("Invalid command");
        }

        return command.execute(arguments);
    }
}