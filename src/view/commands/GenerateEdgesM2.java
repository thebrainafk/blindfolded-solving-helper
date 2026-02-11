package view.commands;

import model.CubeManager;
import model.CubeState;
import model.GameArgumentException;
import model.TranslationGenerator;
import model.cube.Tile;
import resources.TileOrderComparatorFactory;
import view.Result;

import java.util.List;

public class GenerateEdgesM2 extends Command {

    private static final String COMMAND_NAME = "generateEdgesM2";

    public GenerateEdgesM2(CubeManager cubeManager) {
        super(cubeManager, COMMAND_NAME);
    }

    @Override
    public Result execute(String arguments) {
        CubeState cubeState = this.getCurrentCubeState();
        String translation;

        try {
            TranslationGenerator translationGenerator = new TranslationGenerator(cubeState, cubeManager.isMemoryHelperEnabled(), Tile.u,
                    TileOrderComparatorFactory.fromResource("resources/edges_M2_tile_order.txt"));
            List<Tile> M2TileSequence = this.cubeManager.generateEdgesM2TileSequence(cubeState, translationGenerator);
            translation = translationGenerator.translateEdgesM2(M2TileSequence);
        } catch (GameArgumentException error) {
            return Result.error(error.getMessage());
        }

        return Result.edge(translation);
    }

}
