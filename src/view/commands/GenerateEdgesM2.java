package view.commands;

import model.CubeManager;
import model.GameArgumentException;
import view.Result;

public class GenerateEdgesM2 extends Command {

    private static final String COMMAND_NAME = "generateEdgesM2";

    public GenerateEdgesM2(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {
        String translation;

        try {
            translation = this.cubeManager.generateEdgesM2(this.cubeState);
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }

        return Result.ok(translation, this.cubeState);
    }

}
