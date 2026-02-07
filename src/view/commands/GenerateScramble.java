package view.commands;

import model.CubeManager;
import model.GameArgumentException;
import view.Result;

/**
 * asdf.
 *
 * @author uckhu
 */
public class GenerateScramble extends Command {

    private static final String COMMAND_NAME = "generateScramble";

    public GenerateScramble(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {


        try {
            this.cubeManager.generateScramble();
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }

        return Result.ok("generateScramble message");
    }
}
