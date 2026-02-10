package model;

import model.cube.Corner;
import model.cube.CornerPiece;
import model.cube.Cube;
import model.cube.Edge;
import model.cube.EdgePiece;

import java.util.Map;

public class CubeState {
    private final Map<CornerPiece, Corner> corners;
    private final Map<EdgePiece, Edge> edges;

    public CubeState(Cube cube) {
        this.corners = cube.getCorners();
        this.edges = cube.getEdges();
    }

    public Map<CornerPiece, Corner> getCorners() {
        return this.corners;
    }

    public Map<EdgePiece, Edge> getEdges() {
        return this.edges;
    }
}
