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
 * Represents the GenerateEdgesPochmann class.
 */
public class GenerateEdgesPochmann extends Command {

    private static final String COMMAND_NAME = "generateEdgesPochmann";

    /**
     * Creates a new GenerateEdgesPochmann instance.
     */
    public GenerateEdgesPochmann(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {
        CubeState cubeState = this.getCurrentCubeState();
        String translation;
        String setupMoves;

        try {
            TranslationGenerator translationGenerator = new TranslationGenerator(cubeState, cubeManager.isMemoryHelperEnabled(), Tile.b,
                    TileOrderComparatorFactory.fromResource("resources/edges_pochmann_tile_order.txt"));
            List<Tile> tileSequence = this.cubeManager.generateSwappedTileSequenceWhenParity(cubeState, translationGenerator);
            translation = translationGenerator.translateTilePairs(tileSequence);

            SetupMoveGenerator setupMoveGenerator = new SetupMoveGenerator("resources/edges_pochmann_setup_moves.txt");
            setupMoves = setupMoveGenerator.generateFromTileSequence(tileSequence);
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }

        return Result.edge(translation, setupMoves);
    }

}
