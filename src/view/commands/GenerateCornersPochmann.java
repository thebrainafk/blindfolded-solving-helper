package view.commands;

import model.CubeManager;
import model.GameArgumentException;
import view.Result;

public class GenerateCornersPochmann extends Command {

    private static final String COMMAND_NAME = "generateCornersPochmann";

    public GenerateCornersPochmann(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {


        try {
            this.cubeManager.generateCornersPochmann();
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }

        return Result.ok("generateCornersPochmann message");
    }

}
