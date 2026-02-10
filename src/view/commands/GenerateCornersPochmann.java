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
        String translation;

        try {
            translation = this.cubeManager.generateCornersPochmann(this.cubeState);
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }

        return Result.ok(translation, this.cubeState);
    }

}
