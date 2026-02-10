package view.commands;

import model.CubeManager;

import view.Result;

public class ResetCube extends Command {

    private static final String COMMAND_NAME = "resetCube";

    public ResetCube(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {

        this.cubeManager.resetCube();

        return Result.none(this.getCurrentCubeState());
    }

}
