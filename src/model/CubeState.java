package model;

import java.util.Map;

public class CubeState {
    private final Map<CornerPiece, Corner> corners;
    private final Map<EdgePiece, Edge> edges;

    public CubeState(Cube cube) {
        this.corners = cube.getCorners();
        this.edges = cube.getEdges();
    }

    public Map<CornerPiece, Corner> getCorners() {
        return corners;
    }

    public Map<EdgePiece, Edge> getEdges() {
        return edges;
    }
}
