package model;

import view.MovesParser;

import java.util.HashMap;
import java.util.Map;

public class Cube {
    private final Map<CornerPiece, Corner> corners;
    private final Map<EdgePiece, Edge> edges;


    public Cube() {
        this.corners = new HashMap<>();
        this.edges = new HashMap<>();
        this.initialize();
    }

    private void initialize() {
        for (CornerPiece cornerPiece : CornerPiece.values()) {
            this.addCorner(new Corner(cornerPiece));
        }
        for (EdgePiece edgePiece : EdgePiece.values()) {
            this.addEdge(new Edge(edgePiece));
        }

    }

    public Map<CornerPiece, Corner> getCorners() {
        return new HashMap<>(corners);
    }

    public Map<EdgePiece, Edge> getEdges() {
        return new HashMap<>(edges);
    }

    private void addEdge(Edge edge) {
        this.edges.put(edge.getEdge(), edge);
    }

    private void addCorner(Corner corner) {
        this.corners.put(corner.getCorner(), corner);
    }

    public void turn(MovesParser.AllMoves move) {
        Corner firstCorner = null;
        Corner secondCorner = null;
        Corner thirdCorner = null;
        Corner fourthCorner = null;
        Edge firstEdge = null;
        Edge secondEdge = null;
        Edge thirdEdge = null;
        Edge fourthEdge = null;

        switch (move.getMove()) {
            case L -> {
                firstCorner = corners.get(CornerPiece.CORNER_A);
                secondCorner = corners.get(firstCorner.getCorner().getLeft());
                thirdCorner = corners.get(secondCorner.getCorner().getLeft());
                fourthCorner = corners.get(thirdCorner.getCorner().getLeft());
                firstEdge = edges.get(EdgePiece.EDGE_D);
                secondEdge = edges.get(firstEdge.getEdge().getLeft());
                thirdEdge = edges.get(secondEdge.getEdge().getLeft());
                fourthEdge = edges.get(thirdEdge.getEdge().getLeft());
            }
            case R -> {
                firstCorner = corners.get(CornerPiece.CORNER_C);
                secondCorner = corners.get(firstCorner.getCorner().getRight());
                thirdCorner = corners.get(secondCorner.getCorner().getRight());
                fourthCorner = corners.get(thirdCorner.getCorner().getRight());
                firstEdge = edges.get(EdgePiece.EDGE_B);
                secondEdge = edges.get(firstEdge.getEdge().getRight());
                thirdEdge = edges.get(secondEdge.getEdge().getRight());
                fourthEdge = edges.get(thirdEdge.getEdge().getRight());
            }
            case U -> {
                firstCorner = corners.get(CornerPiece.CORNER_C);
                secondCorner = corners.get(firstCorner.getCorner().getUp());
                thirdCorner = corners.get(secondCorner.getCorner().getUp());
                fourthCorner = corners.get(thirdCorner.getCorner().getUp());
                firstEdge = edges.get(EdgePiece.EDGE_C);
                secondEdge = edges.get(firstEdge.getEdge().getUp());
                thirdEdge = edges.get(secondEdge.getEdge().getUp());
                fourthEdge = edges.get(thirdEdge.getEdge().getUp());
            }
            case D -> {
                firstCorner = corners.get(CornerPiece.CORNER_U);
                secondCorner = corners.get(firstCorner.getCorner().getDown());
                thirdCorner = corners.get(secondCorner.getCorner().getDown());
                fourthCorner = corners.get(thirdCorner.getCorner().getDown());
                firstEdge = edges.get(EdgePiece.EDGE_U);
                secondEdge = edges.get(firstEdge.getEdge().getDown());
                thirdEdge = edges.get(secondEdge.getEdge().getDown());
                fourthEdge = edges.get(thirdEdge.getEdge().getDown());
            }
            case F -> {
                firstCorner = corners.get(CornerPiece.CORNER_D);
                secondCorner = corners.get(firstCorner.getCorner().getFront());
                thirdCorner = corners.get(secondCorner.getCorner().getFront());
                fourthCorner = corners.get(thirdCorner.getCorner().getFront());
                firstEdge = edges.get(EdgePiece.EDGE_C);
                secondEdge = edges.get(firstEdge.getEdge().getFront());
                thirdEdge = edges.get(secondEdge.getEdge().getFront());
                fourthEdge = edges.get(thirdEdge.getEdge().getFront());
            }
            case B -> {
                firstCorner = corners.get(CornerPiece.CORNER_B);
                secondCorner = corners.get(firstCorner.getCorner().getBack());
                thirdCorner = corners.get(secondCorner.getCorner().getBack());
                fourthCorner = corners.get(thirdCorner.getCorner().getBack());
                firstEdge = edges.get(EdgePiece.EDGE_A);
                secondEdge = edges.get(firstEdge.getEdge().getBack());
                thirdEdge = edges.get(secondEdge.getEdge().getBack());
                fourthEdge = edges.get(thirdEdge.getEdge().getBack());
            }
        }

        Corner firstCornerSnapshot = new Corner(firstCorner);
        Corner secondCornerSnapshot = new Corner(secondCorner);
        Corner thirdCornerSnapshot = new Corner(thirdCorner);
        Corner fourthCornerSnapshot = new Corner(fourthCorner);

        Edge firstEdgeSnapshot = new Edge(firstEdge);
        Edge secondEdgeSnapshot = new Edge(secondEdge);
        Edge thirdEdgeSnapshot = new Edge(thirdEdge);
        Edge fourthEdgeSnapshot = new Edge(fourthEdge);

        switch (move.getMove()) {
            case L -> {
                secondCorner.turnLeft(firstCornerSnapshot);
                thirdCorner.turnLeft(secondCornerSnapshot);
                fourthCorner.turnLeft(thirdCornerSnapshot);
                firstCorner.turnLeft(fourthCornerSnapshot);
                secondEdge.turnLeft(firstEdgeSnapshot);
                thirdEdge.turnLeft(secondEdgeSnapshot);
                fourthEdge.turnLeft(thirdEdgeSnapshot);
                firstEdge.turnLeft(fourthEdgeSnapshot);
            }
            case R -> {
                secondCorner.turnRight(firstCornerSnapshot);
                thirdCorner.turnRight(secondCornerSnapshot);
                fourthCorner.turnRight(thirdCornerSnapshot);
                firstCorner.turnRight(fourthCornerSnapshot);
                secondEdge.turnRight(firstEdgeSnapshot);
                thirdEdge.turnRight(secondEdgeSnapshot);
                fourthEdge.turnRight(thirdEdgeSnapshot);
                firstEdge.turnRight(fourthEdgeSnapshot);
            }
            case U -> {
                secondCorner.turnUp(firstCornerSnapshot);
                thirdCorner.turnUp(secondCornerSnapshot);
                fourthCorner.turnUp(thirdCornerSnapshot);
                firstCorner.turnUp(fourthCornerSnapshot);
                secondEdge.turnUp(firstEdgeSnapshot);
                thirdEdge.turnUp(secondEdgeSnapshot);
                fourthEdge.turnUp(thirdEdgeSnapshot);
                firstEdge.turnUp(fourthEdgeSnapshot);
            }
            case D -> {
                secondCorner.turnDown(firstCornerSnapshot);
                thirdCorner.turnDown(secondCornerSnapshot);
                fourthCorner.turnDown(thirdCornerSnapshot);
                firstCorner.turnDown(fourthCornerSnapshot);
                secondEdge.turnDown(firstEdgeSnapshot);
                thirdEdge.turnDown(secondEdgeSnapshot);
                fourthEdge.turnDown(thirdEdgeSnapshot);
                firstEdge.turnDown(fourthEdgeSnapshot);
            }
            case F -> {
                secondCorner.turnFront(firstCornerSnapshot);
                thirdCorner.turnFront(secondCornerSnapshot);
                fourthCorner.turnFront(thirdCornerSnapshot);
                firstCorner.turnFront(fourthCornerSnapshot);
                secondEdge.turnFront(firstEdgeSnapshot);
                thirdEdge.turnFront(secondEdgeSnapshot);
                fourthEdge.turnFront(thirdEdgeSnapshot);
                firstEdge.turnFront(fourthEdgeSnapshot);
            }
            case B -> {
                secondCorner.turnBack(firstCornerSnapshot);
                thirdCorner.turnBack(secondCornerSnapshot);
                fourthCorner.turnBack(thirdCornerSnapshot);
                firstCorner.turnBack(fourthCornerSnapshot);
                secondEdge.turnBack(firstEdgeSnapshot);
                thirdEdge.turnBack(secondEdgeSnapshot);
                fourthEdge.turnBack(thirdEdgeSnapshot);
                firstEdge.turnBack(fourthEdgeSnapshot);
            }
        }
    }
}
