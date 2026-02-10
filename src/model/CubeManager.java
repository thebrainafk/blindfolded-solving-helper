package model;


import model.cube.Cube;
import model.cube.Edge;
import model.cube.EdgePiece;
import model.cube.Tile;
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

    public CubeManager() {
        this.cube = new Cube();
        this.memoryHelper = false;
    }

    public void resetCube() {
        this.cube = new Cube();
    }

    public Cube getCube() {
        return this.cube;
    }

    public String generateCornersPochmann(CubeState cubeState) throws GameArgumentException {
        GenerateTranslation generateTranslation = new GenerateTranslation(cubeState, memoryHelper, Tile.E);
        List<Tile> tileSequence = generateTranslation.generateTileSequence();
        return generateTranslation.translatePochmann(tileSequence);
    }

    public String generateEdgesM2(CubeState cubeState) throws GameArgumentException {
        GenerateTranslation cornerTranslator = new GenerateTranslation(cubeState, memoryHelper, Tile.E);
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

        GenerateTranslation generateTranslation = new GenerateTranslation(cubeState, memoryHelper, Tile.u);
        List<Tile> M2TileSequence = generateTranslation.generateTileSequence();

        return generateTranslation.translateEdgesM2(M2TileSequence);
    }

    public String generateEdgesPochmann(CubeState cubeState) throws GameArgumentException {
        GenerateTranslation generateTranslation = new GenerateTranslation(cubeState, memoryHelper, Tile.b);
        List<Tile> tileSequence = generateTranslation.generateTileSequence();
        return generateTranslation.translatePochmann(tileSequence);
    }

    public String generateScramble() throws GameArgumentException {
        SimpleScrambleGenerator simpleScrambleGenerator = new SimpleScrambleGenerator();
        return simpleScrambleGenerator.generateLocalScramble(15);
    }

    public void scrambleCube(List<MovesParser.AllMoves> allMoves) throws GameArgumentException {
        for (MovesParser.AllMoves move : allMoves) {
            for (int i = 0; i < move.getTurns(); i++) {
                this.cube.turn(move);
            }
        }
    }

    public boolean toggleMemoryHelper() {
        this.memoryHelper = !this.memoryHelper;
        return this.memoryHelper;
    }

    public boolean isMemoryHelperEnabled() {
        return this.memoryHelper;
    }
}
