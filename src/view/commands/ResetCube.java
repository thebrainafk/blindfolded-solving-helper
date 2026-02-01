package view.commands;

import model.CubeManager;
import model.GameArgumentException;
import view.Result;

public class ResetCube extends Command {

    private static final String COMMAND_NAME = "resetCube";

    public ResetCube(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String[] arguments) {


        try {
            this.cubeManager.test();
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }

        return Result.ok(this.getCubeState());
    }

}
