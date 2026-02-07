package view.commands;

import model.CubeManager;
import view.Result;

public class ToggleMemoryHelper extends Command {

    private static final String COMMAND_NAME = "toggleMemoryHelper";

    public ToggleMemoryHelper(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {

        this.cubeManager.toggleMemoryHelper();

        return Result.ok("memory helper toggled");
    }

}
