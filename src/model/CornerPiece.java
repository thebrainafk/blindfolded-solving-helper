package model;

import java.util.HashMap;
import java.util.Map;

public enum CornerPiece {
    CORNER_A(Tile.A, Tile.E, Tile.R),
    CORNER_B(Tile.B, Tile.N, Tile.Q),
    CORNER_C(Tile.C, Tile.J, Tile.M),
    CORNER_D(Tile.D, Tile.F, Tile.I),
    CORNER_U(Tile.U, Tile.L, Tile.G),
    CORNER_V(Tile.V, Tile.K, Tile.P),
    CORNER_W(Tile.W, Tile.O, Tile.T),
    CORNER_X(Tile.X, Tile.S, Tile.H);

    private final Tile firstTile;
    private final Tile secondTile;
    private final Tile thirdTile;
    private static final Map<CornerPiece, CornerPiece> turnLeft = new HashMap<>();
    private static final Map<CornerPiece, CornerPiece> turnRight = new HashMap<>();
    private static final Map<CornerPiece, CornerPiece> turnUp = new HashMap<>();
    private static final Map<CornerPiece, CornerPiece> turnDown = new HashMap<>();
    private static final Map<CornerPiece, CornerPiece> turnFront = new HashMap<>();
    private static final Map<CornerPiece, CornerPiece> turnBack = new HashMap<>();

    CornerPiece(Tile firstTiletile, Tile secondTile, Tile thirdTile) {
        this.firstTile = firstTiletile;
        this.secondTile = secondTile;
        this.thirdTile = thirdTile;
        fillMaps();
    }

    private static void fillMaps() {
        //FIXME Not yet mapped correctly
        turnLeft.put(CORNER_A, CORNER_B);
        turnLeft.put(CORNER_B, CORNER_C);
        turnLeft.put(CORNER_C, CORNER_D);
        turnLeft.put(CORNER_D, CORNER_A);
        turnLeft.put(CORNER_U, CORNER_V);
        turnLeft.put(CORNER_V, CORNER_W);
        turnLeft.put(CORNER_W, CORNER_X);
        turnLeft.put(CORNER_X, CORNER_U);

        turnRight.put(CORNER_A, CORNER_D);
        turnRight.put(CORNER_B, CORNER_C);
        turnRight.put(CORNER_C, CORNER_B);
        turnRight.put(CORNER_D, CORNER_A);
        turnRight.put(CORNER_U, CORNER_X);
        turnRight.put(CORNER_V, CORNER_W);
        turnRight.put(CORNER_W, CORNER_V);
        turnRight.put(CORNER_X, CORNER_U);

        turnUp.put(CORNER_A, CORNER_U);
        turnUp.put(CORNER_B, CORNER_V);
        turnUp.put(CORNER_C, CORNER_W);
        turnUp.put(CORNER_D, CORNER_X);
        turnUp.put(CORNER_U, CORNER_A);
        turnUp.put(CORNER_V, CORNER_B);
        turnUp.put(CORNER_W, CORNER_C);
        turnUp.put(CORNER_X, CORNER_D);

        turnDown.put(CORNER_A, CORNER_X);
        turnDown.put(CORNER_B, CORNER_C);
        turnDown.put(CORNER_C, CORNER_D);
        turnDown.put(CORNER_D, CORNER_A);
        turnDown.put(CORNER_U, CORNER_W);
        turnDown.put(CORNER_V, CORNER_V);
        turnDown.put(CORNER_W, CORNER_U);
        turnDown.put(CORNER_X, CORNER_V);

        turnFront.put(CORNER_A, CORNER_U);
        turnFront.put(CORNER_B, CORNER_V);
        turnFront.put(CORNER_C, CORNER_W);
        turnFront.put(CORNER_D, CORNER_X);
        turnFront.put(CORNER_U, CORNER_A);
        turnFront.put(CORNER_V, CORNER_B);
        turnFront.put(CORNER_W, CORNER_C);
        turnFront.put(CORNER_X, CORNER_D);

        turnBack.put(CORNER_A, CORNER_X);
        turnBack.put(CORNER_B, CORNER_C);
        turnBack.put(CORNER_C, CORNER_D);
        turnBack.put(CORNER_D, CORNER_A);
        turnBack.put(CORNER_U, CORNER_W);
        turnBack.put(CORNER_V, CORNER_V);
        turnBack.put(CORNER_W, CORNER_U);
        turnBack.put(CORNER_X, CORNER_V);
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
        return this.firstTile;
    }

    public Tile getSecondTile() {
        return this.secondTile;
    }

    public Tile getThirdTile() {
        return this.thirdTile;
    }

    public CornerPiece getLeft() {
        return turnLeft.get(this);
    }

    public CornerPiece getRight() {
        return turnRight.get(this);
    }

    public CornerPiece getUp() {
        return turnUp.get(this);
    }

    public CornerPiece getDown() {
        return turnDown.get(this);
    }

    public CornerPiece getFront() {
        return turnFront.get(this);
    }

    public CornerPiece getBack() {
        return turnBack.get(this);
    }
}
