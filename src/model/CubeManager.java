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

    public String test() throws GameArgumentException {
        return "test";
    }

    public Cube getCube() {
        if (cube == null) {
            cube = new Cube();
        }
        return cube;
    }
}
