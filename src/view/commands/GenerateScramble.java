package view.commands;

import model.CubeManager;
import model.GameArgumentException;
import view.Result;

/**
 * Represents the GenerateScramble class.
 */
public class GenerateScramble extends Command {

    private static final String COMMAND_NAME = "generateScramble";

    /**
     * Creates a new GenerateScramble instance.
     */
    public GenerateScramble(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {
        String scramble;

        try {
            scramble = this.cubeManager.generateScramble();
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }
        return Result.scramble(scramble);
    }
}
