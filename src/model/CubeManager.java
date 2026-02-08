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
        try {
            for (MovesParser.AllMoves move : allMoves) {
                switch (move.getMove()) {
                    case R -> this.cube.turnRight(move.getTurns());
                    case L -> this.cube.turnLeft(move.getTurns());
                    case U -> this.cube.turnUp(move.getTurns());
                    case D -> this.cube.turnDown(move.getTurns());
                    case F -> this.cube.turnFront(move.getTurns());
                    case B -> this.cube.turnBack(move.getTurns());
                }
            }
        } catch (Exception e) {
            throw new GameArgumentException(e.getMessage());
        }
    }

    public void toggleMemoryHelper() {
        this.memoryHelper = !this.memoryHelper;
    }
}
