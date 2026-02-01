package model;


/**
 * asdf.
 *
 * @author uckhu
 */
public class CubeManager {
    private final Cube cube;

    public CubeManager() {
        this.cube = new Cube();
    }

    public String test() throws GameArgumentException {
        return "test";
    }

    public Cube getCube() {
        return cube;
    }
}
