package view.commands;

import model.CubeManager;

import model.GameArgumentException;
import view.MovesParser;
import view.Result;

import java.util.List;

/**
 * Represents the ScrambleCube class.
 */
public class ScrambleCube extends Command {

    private static final String COMMAND_NAME = "scrambleCube";

    /**
     * Creates a new ScrambleCube instance.
     */
    public ScrambleCube(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {
        if (arguments.isEmpty()) {
            return Result.error("no scramble given");
        }
        if (arguments.isBlank()) {
            return Result.error("no scramble given blank");
        }

        try {
            List<MovesParser.AllMoves> allMoves = MovesParser.getMovesFromScramble(arguments);
            this.cubeManager.scrambleCube(allMoves);
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }

        return Result.scramble(arguments.trim());
    }

}
