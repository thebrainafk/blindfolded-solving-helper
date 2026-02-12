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
 * Central facade for cube state mutations and algorithm-related helper operations.
 */
public class CubeManager {
    private Cube cube;
    private boolean memoryHelper;

    /**
     * Creates a cube manager with a solved cube and disabled memory helper.
     */
    public CubeManager() {
        this.cube = new Cube();
        this.memoryHelper = false;
    }

    /**
     * Resets the managed cube to the solved state.
     */
    public void resetCube() {
        this.cube = new Cube();
    }

    /**
     * Returns the currently managed cube instance.
     *
     * @return mutable cube model
     */
    public Cube getCube() {
        return this.cube;
    }

    /**
     * Generates the translated edge tile sequence and applies parity handling when required.
     *
     * @param cubeState snapshot used for translation
     * @param translationGenerator configured edge translation generator
     * @return translated edge tile sequence
     * @throws GameArgumentException if resources for translation cannot be loaded
     */
    public List<Tile> generateSwappedTileSequenceWhenParity(CubeState cubeState, TranslationGenerator translationGenerator) throws GameArgumentException {
        TranslationGenerator cornerTranslator = new TranslationGenerator(cubeState, memoryHelper, Tile.E,
                TileOrderComparatorFactory.fromResource("resources/corners_pochmann_tile_order.txt"));
        List<Tile> CornerTileSequence = cornerTranslator.generateTileSequence();

        swapWhenParity(cubeState, CornerTileSequence);

        return translationGenerator.generateTileSequence();
    }

    private static void swapWhenParity(CubeState cubeState, List<Tile> CornerTileSequence) {
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
    }

    /**
     * Generates a random scramble with 20 moves.
     *
     * @return scramble string
     * @throws GameArgumentException if scramble generation fails
     */
    public String generateScramble() throws GameArgumentException {
        SimpleScrambleGenerator simpleScrambleGenerator = new SimpleScrambleGenerator();
        return simpleScrambleGenerator.generateLocalScramble(20);
    }

    /**
     * Applies all parsed moves to the managed cube.
     *
     * @param allMoves parsed move sequence in internal representation
     * @throws GameArgumentException if a move cannot be applied
     */
    public void scrambleCube(List<MovesParser.AllMoves> allMoves) throws GameArgumentException {
        for (MovesParser.AllMoves move : allMoves) {
            for (int i = 0; i < move.getTurns(); i++) {
                this.cube.turn(move);
            }
        }
    }

    /**
     * Toggles the memory-helper mode used by translation output.
     */
    public void toggleMemoryHelper() {
        this.memoryHelper = !this.memoryHelper;
    }

    /**
     * Indicates whether memory-helper output is currently enabled.
     *
     * @return {@code true} if helper mode is active
     */
    public boolean isMemoryHelperEnabled() {
        return this.memoryHelper;
    }
}
