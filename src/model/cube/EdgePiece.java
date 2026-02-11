package model.cube;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the EdgePiece enum.
 */
public enum EdgePiece {
    EDGE_A(Tile.a, Tile.q),
    EDGE_B(Tile.b, Tile.m),
    EDGE_C(Tile.c, Tile.i),
    EDGE_D(Tile.d, Tile.e),
    EDGE_F(Tile.f, Tile.l),
    EDGE_H(Tile.h, Tile.r),
    EDGE_N(Tile.n, Tile.t),
    EDGE_P(Tile.p, Tile.j),
    EDGE_U(Tile.u, Tile.k),
    EDGE_V(Tile.v, Tile.o),
    EDGE_W(Tile.w, Tile.s),
    EDGE_X(Tile.x, Tile.g);

    private final Tile firstTile;
    private final Tile secondTile;
    private static final Map<EdgePiece, EdgePiece> turnLeft = new HashMap<>();
    private static final Map<EdgePiece, EdgePiece> turnRight = new HashMap<>();
    private static final Map<EdgePiece, EdgePiece> turnUp = new HashMap<>();
    private static final Map<EdgePiece, EdgePiece> turnDown = new HashMap<>();
    private static final Map<EdgePiece, EdgePiece> turnFront = new HashMap<>();
    private static final Map<EdgePiece, EdgePiece> turnBack = new HashMap<>();

    EdgePiece(Tile firstTile, Tile secondTile) {
        this.firstTile = firstTile;
        this.secondTile = secondTile;
    }

    static {
        fillMaps();
    }

    private static void fillMaps() {
        turnLeft.put(EDGE_D, EDGE_F);
        turnLeft.put(EDGE_F, EDGE_X);
        turnLeft.put(EDGE_X, EDGE_H);
        turnLeft.put(EDGE_H, EDGE_D);

        turnRight.put(EDGE_B, EDGE_N);
        turnRight.put(EDGE_N, EDGE_V);
        turnRight.put(EDGE_V, EDGE_P);
        turnRight.put(EDGE_P, EDGE_B);

        turnUp.put(EDGE_C, EDGE_D);
        turnUp.put(EDGE_D, EDGE_A);
        turnUp.put(EDGE_A, EDGE_B);
        turnUp.put(EDGE_B, EDGE_C);

        turnDown.put(EDGE_U, EDGE_V);
        turnDown.put(EDGE_V, EDGE_W);
        turnDown.put(EDGE_W, EDGE_X);
        turnDown.put(EDGE_X, EDGE_U);

        turnFront.put(EDGE_C, EDGE_P);
        turnFront.put(EDGE_P, EDGE_U);
        turnFront.put(EDGE_U, EDGE_F);
        turnFront.put(EDGE_F, EDGE_C);

        turnBack.put(EDGE_A, EDGE_H);
        turnBack.put(EDGE_H, EDGE_W);
        turnBack.put(EDGE_W, EDGE_N);
        turnBack.put(EDGE_N, EDGE_A);
    }

    /**
     * Executes getPieceFromTile.
     */
    public static EdgePiece getPieceFromTile(Tile tile) {
        for (EdgePiece piece : EdgePiece.values()) {
            if (piece.firstTile == tile || piece.secondTile == tile) {
                return piece;
            }
        }
        return null;
    }

    /**
     * Executes getFirstTile.
     */
    public Tile getFirstTile() {
        return this.firstTile;
    }

    /**
     * Executes getSecondTile.
     */
    public Tile getSecondTile() {
        return this.secondTile;
    }

    /**
     * Executes getLeft.
     */
    public EdgePiece getLeft() {
        return turnLeft.get(this);
    }

    /**
     * Executes getRight.
     */
    public EdgePiece getRight() {
        return turnRight.get(this);
    }

    /**
     * Executes getUp.
     */
    public EdgePiece getUp() {
        return turnUp.get(this);
    }

    /**
     * Executes getDown.
     */
    public EdgePiece getDown() {
        return turnDown.get(this);
    }

    /**
     * Executes getFront.
     */
    public EdgePiece getFront() {
        return turnFront.get(this);
    }

    /**
     * Executes getBack.
     */
    public EdgePiece getBack() {
        return turnBack.get(this);
    }
}
