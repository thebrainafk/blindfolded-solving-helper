package view.commands;

import model.CubeManager;
import view.Result;

/**
 * Simple command used for testing web execution.
 */
public class Status extends Command {
    private static final String COMMAND_NAME = "status";

    public Status(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {
        return Result.ok("status executed.");
    }
}
