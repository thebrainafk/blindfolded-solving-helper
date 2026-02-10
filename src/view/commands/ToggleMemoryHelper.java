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

        boolean memoryHelper = this.cubeManager.toggleMemoryHelper();
        String message = memoryHelper ? "memory helper enabled" : "memory helper disabled";

        return Result.ok(message, this.getCurrentCubeState());
    }

}
