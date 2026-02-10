package view.commands;

import model.CubeManager;
import model.CubeState;
import model.GameArgumentException;
import view.Result;

public class GenerateEdgesPochmann extends Command {

    private static final String COMMAND_NAME = "generateEdgesPochmann";

    public GenerateEdgesPochmann(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {
        CubeState cubeState = this.getCurrentCubeState();
        String translation;

        try {
            translation = this.cubeManager.generateEdgesPochmann(cubeState);
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }

        return Result.edge(translation, cubeState);
    }

}
