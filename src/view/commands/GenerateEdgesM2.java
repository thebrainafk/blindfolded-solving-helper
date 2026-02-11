package view.commands;

import model.CubeManager;
import model.CubeState;
import model.GameArgumentException;
import model.SetupMoveGenerator;
import model.TranslationGenerator;
import model.cube.Tile;
import resources.TileOrderComparatorFactory;
import view.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the GenerateEdgesM2 class.
 */
public class GenerateEdgesM2 extends Command {

    private static final String COMMAND_NAME = "generateEdgesM2";

    /**
     * Creates a new GenerateEdgesM2 instance.
     */
    public GenerateEdgesM2(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    /**
     * Executes execute.
     */
    public Result execute(String arguments) {
        CubeState cubeState = this.getCurrentCubeState();
        String translation;
        String setupMoves;

        try {
            TranslationGenerator translationGenerator = new TranslationGenerator(cubeState, cubeManager.isMemoryHelperEnabled(), Tile.u,
                    TileOrderComparatorFactory.fromResource("resources/edges_M2_tile_order.txt"));
            List<Tile> M2TileSequence = this.cubeManager.generateEdgesM2TileSequence(cubeState, translationGenerator);
            List<Tile> transformedTileSequence = this.applyOppositeTileRule(M2TileSequence);
            translation = translationGenerator.translateTilePairs(transformedTileSequence);
            SetupMoveGenerator setupMoveGenerator = new SetupMoveGenerator("resources/edges_M2_setup_moves.txt");
            setupMoves = setupMoveGenerator.generateFromTileSequence(transformedTileSequence);
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }

        return Result.edge(translation, setupMoves);
    }

    private List<Tile> applyOppositeTileRule(List<Tile> tileSequence) {
        List<Tile> transformedTileSequence = new ArrayList<>(tileSequence);
        for (int i = 1; i < transformedTileSequence.size(); i = i + 2) {
            Tile opposite = transformedTileSequence.get(i).getOppositeTile();
            if (opposite != null) {
                transformedTileSequence.set(i, opposite);
            }
        }
        return transformedTileSequence;
    }
}
