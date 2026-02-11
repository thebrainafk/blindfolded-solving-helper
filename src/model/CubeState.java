package model;

import model.cube.Corner;
import model.cube.CornerPiece;
import model.cube.Cube;
import model.cube.Edge;
import model.cube.EdgePiece;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the CubeState class.
 */
public class CubeState {
    private final Map<CornerPiece, Corner> corners;
    private final Map<EdgePiece, Edge> edges;

    /**
     * Creates a new CubeState instance.
     */
    public CubeState(Cube cube) {
        this.corners = new HashMap<>();
        this.edges = new HashMap<>();

        for (Map.Entry<CornerPiece, Corner> cornerEntry : cube.getCorners().entrySet()) {
            this.corners.put(cornerEntry.getKey(), new Corner(cornerEntry.getValue()));
        }

        for (Map.Entry<EdgePiece, Edge> edgeEntry : cube.getEdges().entrySet()) {
            this.edges.put(edgeEntry.getKey(), new Edge(edgeEntry.getValue()));
        }
    }

    /**
     * Executes getCorners.
     */
    public Map<CornerPiece, Corner> getCorners() {
        return this.corners;
    }

    /**
     * Executes getEdges.
     */
    public Map<EdgePiece, Edge> getEdges() {
        return this.edges;
    }
}
