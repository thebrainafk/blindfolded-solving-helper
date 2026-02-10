package view.commands;

import model.CubeManager;

import model.GameArgumentException;
import view.MovesParser;
import view.Result;

import java.util.List;

public class ScrambleCube extends Command {

    private static final String COMMAND_NAME = "scrambleCube";

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

        List<MovesParser.AllMoves> allMoves = MovesParser.getMovesFromScramble(arguments);
        StringBuilder stringBuilder = new StringBuilder();
        for (MovesParser.AllMoves move : allMoves) {
            stringBuilder.append(move.name());
            stringBuilder.append(" ");
        }

        try {
            this.cubeManager.scrambleCube(allMoves);
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }

        return Result.ok(stringBuilder.toString(), this.getCurrentCubeState());
    }

}
