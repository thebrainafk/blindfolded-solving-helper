package model;

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

    public void turnLeft(int turns) {
        for (int i = 0; i < turns; i++) {
            Corner firstCorner = corners.get(CornerPiece.CORNER_A);
            Corner secondCorner = corners.get(firstCorner.getCorner().getLeft());
            Corner thirdCorner = corners.get(secondCorner.getCorner().getLeft());
            Corner fourthCorner = corners.get(thirdCorner.getCorner().getLeft());
            secondCorner.turnLeft(firstCorner);
            thirdCorner.turnLeft(secondCorner);
            fourthCorner.turnLeft(thirdCorner);
            firstCorner.turnLeft(fourthCorner);

            Edge firstEdge = edges.get(EdgePiece.EDGE_D);
            Edge secondEdge = edges.get(firstEdge.getEdge().getLeft());
            Edge thirdEdge = edges.get(secondEdge.getEdge().getLeft());
            Edge fourthEdge = edges.get(thirdEdge.getEdge().getLeft());
            secondEdge.turnLeft(firstEdge);
            thirdEdge.turnLeft(secondEdge);
            fourthEdge.turnLeft(thirdEdge);
            firstEdge.turnLeft(fourthEdge);
        }
    }

    public void turnRight(int turns) {
        for (int i = 0; i < turns; i++) {
            Corner firstCorner = corners.get(CornerPiece.CORNER_C);
            Corner secondCorner = corners.get(firstCorner.getCorner().getRight());
            Corner thirdCorner = corners.get(secondCorner.getCorner().getRight());
            Corner fourthCorner = corners.get(thirdCorner.getCorner().getRight());
            secondCorner.turnRight(firstCorner);
            thirdCorner.turnRight(secondCorner);
            fourthCorner.turnRight(thirdCorner);
            firstCorner.turnRight(fourthCorner);

            Edge firstEdge = edges.get(EdgePiece.EDGE_B);
            System.out.println(firstEdge.getEdge());
            Edge secondEdge = edges.get(firstEdge.getEdge().getRight());
            System.out.println(secondEdge.getEdge());
            Edge thirdEdge = edges.get(secondEdge.getEdge().getRight());
            System.out.println(thirdEdge.getEdge());
            Edge fourthEdge = edges.get(thirdEdge.getEdge().getRight());
            System.out.println(fourthEdge.getEdge());
            secondEdge.turnRight(firstEdge);
            thirdEdge.turnRight(secondEdge);
            fourthEdge.turnRight(thirdEdge);
            firstEdge.turnRight(fourthEdge);
        }
    }

    public void turnUp(int turns) {
        for (int i = 0; i < turns; i++) {
            Corner firstCorner = corners.get(CornerPiece.CORNER_C);
            Corner secondCorner = corners.get(firstCorner.getCorner().getUp());
            Corner thirdCorner = corners.get(secondCorner.getCorner().getUp());
            Corner fourthCorner = corners.get(thirdCorner.getCorner().getUp());
            secondCorner.turnUp(firstCorner);
            thirdCorner.turnUp(secondCorner);
            fourthCorner.turnUp(thirdCorner);
            firstCorner.turnUp(fourthCorner);

            Edge firstEdge = edges.get(EdgePiece.EDGE_C);
            Edge secondEdge = edges.get(firstEdge.getEdge().getUp());
            Edge thirdEdge = edges.get(secondEdge.getEdge().getUp());
            Edge fourthEdge = edges.get(thirdEdge.getEdge().getUp());
            secondEdge.turnUp(firstEdge);
            thirdEdge.turnUp(secondEdge);
            fourthEdge.turnUp(thirdEdge);
            firstEdge.turnUp(fourthEdge);
        }
    }

    public void turnDown(int turns) {
        for (int i = 0; i < turns; i++) {
            Corner firstCorner = corners.get(CornerPiece.CORNER_U);
            Corner secondCorner = corners.get(firstCorner.getCorner().getDown());
            Corner thirdCorner = corners.get(secondCorner.getCorner().getDown());
            Corner fourthCorner = corners.get(thirdCorner.getCorner().getDown());
            secondCorner.turnDown(firstCorner);
            thirdCorner.turnDown(secondCorner);
            fourthCorner.turnDown(thirdCorner);
            firstCorner.turnDown(fourthCorner);

            Edge firstEdge = edges.get(EdgePiece.EDGE_U);
            Edge secondEdge = edges.get(firstEdge.getEdge().getDown());
            Edge thirdEdge = edges.get(secondEdge.getEdge().getDown());
            Edge fourthEdge = edges.get(thirdEdge.getEdge().getDown());
            secondEdge.turnDown(firstEdge);
            thirdEdge.turnDown(secondEdge);
            fourthEdge.turnDown(thirdEdge);
            firstEdge.turnDown(fourthEdge);
        }
    }

    public void turnFront(int turns) {
        for (int i = 0; i < turns; i++) {
            Corner firstCorner = corners.get(CornerPiece.CORNER_D);
            Corner secondCorner = corners.get(firstCorner.getCorner().getFront());
            Corner thirdCorner = corners.get(secondCorner.getCorner().getFront());
            Corner fourthCorner = corners.get(thirdCorner.getCorner().getFront());
            secondCorner.turnFront(firstCorner);
            thirdCorner.turnFront(secondCorner);
            fourthCorner.turnFront(thirdCorner);
            firstCorner.turnFront(fourthCorner);

            Edge firstEdge = edges.get(EdgePiece.EDGE_C);
            Edge secondEdge = edges.get(firstEdge.getEdge().getFront());
            Edge thirdEdge = edges.get(secondEdge.getEdge().getFront());
            Edge fourthEdge = edges.get(thirdEdge.getEdge().getFront());
            secondEdge.turnFront(firstEdge);
            thirdEdge.turnFront(secondEdge);
            fourthEdge.turnFront(thirdEdge);
            firstEdge.turnFront(fourthEdge);
        }
    }

    public void turnBack(int turns) {
        for (int i = 0; i < turns; i++) {
            Corner firstCorner = corners.get(CornerPiece.CORNER_B);
            Corner secondCorner = corners.get(firstCorner.getCorner().getBack());
            Corner thirdCorner = corners.get(secondCorner.getCorner().getBack());
            Corner fourthCorner = corners.get(thirdCorner.getCorner().getBack());
            secondCorner.turnBack(firstCorner);
            thirdCorner.turnBack(secondCorner);
            fourthCorner.turnBack(thirdCorner);
            firstCorner.turnBack(fourthCorner);

            Edge firstEdge = edges.get(EdgePiece.EDGE_A);
            Edge secondEdge = edges.get(firstEdge.getEdge().getBack());
            Edge thirdEdge = edges.get(secondEdge.getEdge().getBack());
            Edge fourthEdge = edges.get(thirdEdge.getEdge().getBack());
            secondEdge.turnBack(firstEdge);
            thirdEdge.turnBack(secondEdge);
            fourthEdge.turnBack(thirdEdge);
            firstEdge.turnBack(fourthEdge);
        }
    }
}
