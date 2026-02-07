package model;


/**
 * asdf.
 *
 * @author uckhu
 */
public class CubeManager {
    private Cube cube;

    public CubeManager() {

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

    public void scrambleCube() throws GameArgumentException {
    }

    public void toggleMemoryHelper() {
    }
}
