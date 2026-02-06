package model;

import java.util.HashMap;
import java.util.Map;

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
        fillMaps();
    }

    private static void fillMaps() {
        //FIXME Not yet mapped correctly
        turnLeft.put(EDGE_A, EDGE_B);
        turnLeft.put(EDGE_B, EDGE_C);
        turnLeft.put(EDGE_C, EDGE_D);
        turnLeft.put(EDGE_D, EDGE_A);
        turnLeft.put(EDGE_F, EDGE_A);
        turnLeft.put(EDGE_H, EDGE_B);
        turnLeft.put(EDGE_N, EDGE_C);
        turnLeft.put(EDGE_P, EDGE_D);
        turnLeft.put(EDGE_U, EDGE_A);
        turnLeft.put(EDGE_V, EDGE_B);
        turnLeft.put(EDGE_W, EDGE_C);
        turnLeft.put(EDGE_X, EDGE_D);

        turnRight.put(EDGE_A, EDGE_D);
        turnRight.put(EDGE_B, EDGE_C);
        turnRight.put(EDGE_C, EDGE_B);
        turnRight.put(EDGE_D, EDGE_A);
        turnRight.put(EDGE_F, EDGE_D);
        turnRight.put(EDGE_H, EDGE_C);
        turnRight.put(EDGE_N, EDGE_B);
        turnRight.put(EDGE_P, EDGE_A);
        turnRight.put(EDGE_U, EDGE_D);
        turnRight.put(EDGE_V, EDGE_C);
        turnRight.put(EDGE_W, EDGE_B);
        turnRight.put(EDGE_X, EDGE_A);

        turnUp.put(EDGE_A, EDGE_F);
        turnUp.put(EDGE_B, EDGE_H);
        turnUp.put(EDGE_C, EDGE_N);
        turnUp.put(EDGE_D, EDGE_P);
        turnUp.put(EDGE_F, EDGE_A);
        turnUp.put(EDGE_H, EDGE_B);
        turnUp.put(EDGE_N, EDGE_C);
        turnUp.put(EDGE_P, EDGE_D);
        turnUp.put(EDGE_U, EDGE_F);
        turnUp.put(EDGE_V, EDGE_W);
        turnUp.put(EDGE_W, EDGE_X);
        turnUp.put(EDGE_X, EDGE_U);

        turnDown.put(EDGE_A, EDGE_P);
        turnDown.put(EDGE_B, EDGE_N);
        turnDown.put(EDGE_C, EDGE_H);
        turnDown.put(EDGE_D, EDGE_F);
        turnDown.put(EDGE_F, EDGE_D);
        turnDown.put(EDGE_H, EDGE_C);
        turnDown.put(EDGE_N, EDGE_B);
        turnDown.put(EDGE_P, EDGE_A);
        turnDown.put(EDGE_U, EDGE_X);
        turnDown.put(EDGE_V, EDGE_W);
        turnDown.put(EDGE_W, EDGE_V);
        turnDown.put(EDGE_X, EDGE_U);

        turnFront.put(EDGE_A, EDGE_U);
        turnFront.put(EDGE_B, EDGE_V);
        turnFront.put(EDGE_C, EDGE_W);
        turnFront.put(EDGE_D, EDGE_X);
        turnFront.put(EDGE_F, EDGE_U);
        turnFront.put(EDGE_H, EDGE_W);
        turnFront.put(EDGE_N, EDGE_X);
        turnFront.put(EDGE_P, EDGE_V);
        turnFront.put(EDGE_U, EDGE_A);
        turnFront.put(EDGE_V, EDGE_B);
        turnFront.put(EDGE_W, EDGE_C);
        turnFront.put(EDGE_X, EDGE_D);

        turnBack.put(EDGE_A, EDGE_X);
        turnBack.put(EDGE_B, EDGE_C);
        turnBack.put(EDGE_C, EDGE_D);
        turnBack.put(EDGE_D, EDGE_A);
        turnBack.put(EDGE_F, EDGE_V);
        turnBack.put(EDGE_H, EDGE_W);
        turnBack.put(EDGE_N, EDGE_X);
        turnBack.put(EDGE_P, EDGE_U);
        turnBack.put(EDGE_U, EDGE_W);
        turnBack.put(EDGE_V, EDGE_X);
        turnBack.put(EDGE_W, EDGE_B);
        turnBack.put(EDGE_X, EDGE_C);
    }

    public static EdgePiece getPieceFromTile(Tile tile) {
        for (EdgePiece piece : EdgePiece.values()) {
            if (piece.firstTile == tile || piece.secondTile == tile) {
                return piece;
            }
        }
        return null;
    }

    public Tile getFirstTile() {
        return this.firstTile;
    }

    public Tile getSecondTile() {
        return this.secondTile;
    }

    public EdgePiece getLeft() {
        return turnLeft.get(this);
    }

    public EdgePiece getRight() {
        return turnRight.get(this);
    }

    public EdgePiece getUp() {
        return turnUp.get(this);
    }

    public EdgePiece getDown() {
        return turnDown.get(this);
    }

    public EdgePiece getFront() {
        return turnFront.get(this);
    }

    public EdgePiece getBack() {
        return turnBack.get(this);
    }
}
