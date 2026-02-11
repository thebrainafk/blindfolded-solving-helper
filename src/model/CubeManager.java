package model;


import model.cube.Cube;
import model.cube.Edge;
import model.cube.EdgePiece;
import model.cube.Tile;
import resources.TileOrderComparatorFactory;
import view.MovesParser;

import java.util.List;
import java.util.Map;

/**
 * asdf.
 *
 * @author uckhu
 */
public class CubeManager {
    private Cube cube;
    private boolean memoryHelper;

    /**
     * Creates a new CubeManager instance.
     */
    public CubeManager() {
        this.cube = new Cube();
        this.memoryHelper = false;
    }

    /**
     * Executes resetCube.
     */
    public void resetCube() {
        this.cube = new Cube();
    }

    /**
     * Executes getCube.
     */
    public Cube getCube() {
        return this.cube;
    }

    /**
     * Executes generateEdgesM2TileSequence.
     */
    public List<Tile> generateEdgesM2TileSequence(CubeState cubeState, TranslationGenerator translationGenerator) throws GameArgumentException {
        TranslationGenerator cornerTranslator = new TranslationGenerator(cubeState, memoryHelper, Tile.E,
                TileOrderComparatorFactory.fromResource("resources/corners_pochmann_tile_order.txt"));
        List<Tile> CornerTileSequence = cornerTranslator.generateTileSequence();

        if (CornerTileSequence.size() % 2 == 1) {
            Map<EdgePiece, Edge> edges = cubeState.getEdges();
            for (Edge edge : edges.values()) {
                if (edge.getCurrentLocation().equals(EdgePiece.EDGE_D)) {
                    edge.swapTileLocations(edge.getEdge().getFirstTile());
                    edge.swapTileLocations(edge.getEdge().getSecondTile());
                }
                if (edge.getCurrentLocation().equals(EdgePiece.EDGE_A)) {
                    edge.swapTileLocations(edge.getEdge().getFirstTile());
                    edge.swapTileLocations(edge.getEdge().getSecondTile());
                }
            }
        }

        return translationGenerator.generateTileSequence();
    }

    /**
     * Executes generateScramble.
     */
    public String generateScramble() throws GameArgumentException {
        SimpleScrambleGenerator simpleScrambleGenerator = new SimpleScrambleGenerator();
        return simpleScrambleGenerator.generateLocalScramble(15);
    }

    /**
     * Executes scrambleCube.
     */
    public void scrambleCube(List<MovesParser.AllMoves> allMoves) throws GameArgumentException {
        for (MovesParser.AllMoves move : allMoves) {
            for (int i = 0; i < move.getTurns(); i++) {
                this.cube.turn(move);
            }
        }
    }

    /**
     * Executes toggleMemoryHelper.
     */
    public void toggleMemoryHelper() {
        this.memoryHelper = !this.memoryHelper;
    }

    /**
     * Executes isMemoryHelperEnabled.
     */
    public boolean isMemoryHelperEnabled() {
        return this.memoryHelper;
    }
}
