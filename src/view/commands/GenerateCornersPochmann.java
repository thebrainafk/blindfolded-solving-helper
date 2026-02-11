package view.commands;

import model.CubeManager;
import model.CubeState;
import model.GameArgumentException;
import model.SetupMoveGenerator;
import model.TranslationGenerator;
import model.cube.Tile;
import resources.TileOrderComparatorFactory;
import view.Result;

import java.util.List;

/**
 * Represents the GenerateCornersPochmann class.
 */
public class GenerateCornersPochmann extends Command {

    private static final String COMMAND_NAME = "generateCornersPochmann";

    /**
     * Creates a new GenerateCornersPochmann instance.
     */
    public GenerateCornersPochmann(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {
        CubeState cubeState = this.getCurrentCubeState();
        String translation;
        String setupMoves;

        try {
            TranslationGenerator translationGenerator = new TranslationGenerator(cubeState, cubeManager.isMemoryHelperEnabled(), Tile.E,
                    TileOrderComparatorFactory.fromResource("resources/corners_pochmann_tile_order.txt"));
            List<Tile> tileSequence = translationGenerator.generateTileSequence();
            translation = translationGenerator.translateTilePairs(tileSequence);
            SetupMoveGenerator setupMoveGenerator = new SetupMoveGenerator("resources/corners_pochmann_setup_moves.txt");
            setupMoves = setupMoveGenerator.generateFromTileSequence(tileSequence);
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }

        return Result.corner(translation, setupMoves);
    }

}
