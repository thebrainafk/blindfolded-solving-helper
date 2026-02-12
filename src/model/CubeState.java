package model;

import model.cube.Corner;
import model.cube.CornerPiece;
import model.cube.Cube;
import model.cube.Edge;
import model.cube.EdgePiece;

import java.util.HashMap;
import java.util.Map;

/**
 * Immutable snapshot-like copy of corners and edges for read-only calculations.
 */
public class CubeState {
    private final Map<CornerPiece, Corner> corners;
    private final Map<EdgePiece, Edge> edges;

    /**
     * Creates a detached copy of the provided cube state.
     *
     * @param cube cube to copy from
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
     * Returns copied corner pieces indexed by their solved position.
     *
     * @return map of corners
     */
    public Map<CornerPiece, Corner> getCorners() {
        return this.corners;
    }

    /**
     * Returns copied edge pieces indexed by their solved position.
     *
     * @return map of edges
     */
    public Map<EdgePiece, Edge> getEdges() {
        return this.edges;
    }
}
