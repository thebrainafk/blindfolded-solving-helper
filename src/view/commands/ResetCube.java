package view.commands;

import model.CubeManager;

import view.Result;

/**
 * Represents the ResetCube class.
 */
public class ResetCube extends Command {

    private static final String COMMAND_NAME = "resetCube";

    /**
     * Creates a new ResetCube instance.
     */
    public ResetCube(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {

        this.cubeManager.resetCube();

        return Result.none();
    }

}
