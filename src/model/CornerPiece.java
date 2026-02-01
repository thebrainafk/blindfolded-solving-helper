package model;

public enum CornerPiece {
    CORNER_A(Tile.A, Tile.E, Tile.R, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A),
    CORNER_B(Tile.B, Tile.N, Tile.Q, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A),
    CORNER_C(Tile.C, Tile.J, Tile.M, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A),
    CORNER_D(Tile.D, Tile.F, Tile.I, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A),
    CORNER_U(Tile.U, Tile.L, Tile.G, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A),
    CORNER_V(Tile.V, Tile.K, Tile.P, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A),
    CORNER_W(Tile.W, Tile.O, Tile.T, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A),
    CORNER_X(Tile.X, Tile.S, Tile.H, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A, CORNER_A);

    private final Tile firstTile;
    private final Tile secondTile;
    private final Tile thirdTile;
    private final CornerPiece left;
    private final CornerPiece right;
    private final CornerPiece up;
    private final CornerPiece down;
    private final CornerPiece front;
    private final CornerPiece back;

    CornerPiece(Tile firstTiletile, Tile secondTile, Tile thirdTile, CornerPiece turnLeft, CornerPiece turnRight, CornerPiece turnUp, CornerPiece turnDown, CornerPiece front, CornerPiece back) {
        this.firstTile = firstTiletile;
        this.secondTile = secondTile;
        this.thirdTile = thirdTile;
        this.left = turnLeft;
        this.right = turnRight;
        this.up = turnUp;
        this.down = turnDown;
        this.front = turnLeft;
        this.back = turnRight;
    }

    public static CornerPiece getPieceFromTile(Tile tile) {
        for (CornerPiece piece : CornerPiece.values()) {
            if (piece.firstTile == tile || piece.secondTile == tile || piece.thirdTile == tile) {
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

    public Tile getThirdTile() {
        return thirdTile;
    }

    public CornerPiece getLeft() {
        return left;
    }

    public CornerPiece getRight() {
        return right;
    }

    public CornerPiece getUp() {
        return up;
    }

    public CornerPiece getDown() {
        return down;
    }

    public CornerPiece getFront() {
        return front;
    }

    public CornerPiece getBack() {
        return back;
    }
}
