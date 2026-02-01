package model;

public enum EdgePiece {
    EDGE_A(Tile.a, Tile.q, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B),
    EDGE_B(Tile.b, Tile.m, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B),
    EDGE_C(Tile.c, Tile.i, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B),
    EDGE_D(Tile.d, Tile.e, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B),
    EDGE_F(Tile.f, Tile.l, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B),
    EDGE_H(Tile.h, Tile.r, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B),
    EDGE_N(Tile.n, Tile.t, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B),
    EDGE_P(Tile.p, Tile.j, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B),
    EDGE_U(Tile.u, Tile.k, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B),
    EDGE_V(Tile.v, Tile.o, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B),
    EDGE_W(Tile.w, Tile.s, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B),
    EDGE_X(Tile.x, Tile.g, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B, EDGE_B);

    private final Tile firstTile;
    private final Tile secondTile;
    private final EdgePiece left;
    private final EdgePiece right;
    private final EdgePiece up;
    private final EdgePiece down;
    private final EdgePiece front;
    private final EdgePiece back;

    EdgePiece(Tile firstTile, Tile secondTile, EdgePiece turnLeft, EdgePiece turnRight, EdgePiece turnUp, EdgePiece turnDown, EdgePiece turnFront, EdgePiece turnBack) {
        this.firstTile = firstTile;
        this.secondTile = secondTile;
        this.left = turnLeft;
        this.right = turnRight;
        this.up = turnUp;
        this.down = turnDown;
        this.front = turnFront;
        this.back = turnBack;
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
        return firstTile;
    }

    public Tile getSecondTile() {
        return secondTile;
    }

    public EdgePiece getLeft() {
        return left;
    }

    public EdgePiece getRight() {
        return right;
    }

    public EdgePiece getUp() {
        return up;
    }

    public EdgePiece getDown() {
        return down;
    }

    public EdgePiece getFront() {
        return front;
    }

    public EdgePiece getBack() {
        return back;
    }
}
