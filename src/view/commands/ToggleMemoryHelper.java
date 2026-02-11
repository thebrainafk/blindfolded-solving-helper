package view.commands;

import model.CubeManager;
import view.Result;

/**
 * Represents the ToggleMemoryHelper class.
 */
public class ToggleMemoryHelper extends Command {

    private static final String COMMAND_NAME = "toggleMemoryHelper";

    /**
     * Creates a new ToggleMemoryHelper instance.
     */
    public ToggleMemoryHelper(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {

        this.cubeManager.toggleMemoryHelper();

        return Result.none();
    }
}
