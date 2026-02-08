package model;


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

    public void generateCornersPochmann() throws GameArgumentException {
    }

    public void generateEdgesM2() throws GameArgumentException {
    }

    public void generateEdgesPochmann() throws GameArgumentException {
    }

    public void generateScramble() throws GameArgumentException {
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
