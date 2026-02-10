package model;


import model.cube.Cube;
import model.cube.Tile;
import view.MovesParser;

import java.util.List;

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

        GenerateTranslation generateTranslation = new GenerateTranslation(cubeState, memoryHelper, Tile.a);
        List<Tile> M2TileSequence = generateTranslation.generateTileSequence();

        if (CornerTileSequence.size() % 2 == 1) {
            for (int i = 0; i < M2TileSequence.size(); i++) {
                Tile tile = M2TileSequence.get(i);
                switch (tile) {
                    case a:
                        M2TileSequence.set(i, Tile.d);
                        break;
                    case d:
                        M2TileSequence.set(i, Tile.a);
                        break;
                    case q:
                        M2TileSequence.set(i, Tile.e);
                        break;
                    case e:
                        M2TileSequence.set(i, Tile.q);
                        break;
                    default:
                        break;
                }
            }
        }
        return generateTranslation.translateEdgesM2(M2TileSequence);
    }

    public String generateEdgesPochmann(CubeState cubeState) throws GameArgumentException {
        GenerateTranslation generateTranslation = new GenerateTranslation(cubeState, memoryHelper, Tile.d);
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

    public void toggleMemoryHelper() {
        this.memoryHelper = !this.memoryHelper;
    }
}
