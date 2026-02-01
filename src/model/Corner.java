package model;

public class Corner extends Piece {
    private final CornerPiece corner;
    private CornerPiece location;

    public Corner(CornerPiece corner) {
        this.corner = corner;
        this.location = corner;
        this.initialize();
    }

    private void initialize() {
        this.tileLocation.put(corner.getFirstTile(), corner.getFirstTile());
        this.tileLocation.put(corner.getSecondTile(), corner.getSecondTile());
        this.tileLocation.put(corner.getThirdTile(), corner.getThirdTile());
    }

    public CornerPiece getLocation() {
        return location;
    }

    public CornerPiece getCorner() {
        return corner;
    }

    private void swap(Corner corner) {
        this.location = corner.getLocation();
    }

    public void turnLeft(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getLeft();
        Tile nextSecondTile = this.corner.getSecondTile().getLeft();
        Tile nextThirdTile = this.corner.getThirdTile().getLeft();
        this.tileLocation.put(this.corner.getFirstTile(), piece.getTileLocation(nextFirstTile));
        this.tileLocation.put(this.corner.getSecondTile(), piece.getTileLocation(nextSecondTile));
        this.tileLocation.put(this.corner.getThirdTile(), piece.getTileLocation(nextThirdTile));
    }

    public void turnRight(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getRight();
        Tile nextSecondTile = this.corner.getSecondTile().getRight();
        Tile nextThirdTile = this.corner.getThirdTile().getRight();
        this.tileLocation.put(this.corner.getFirstTile(), piece.getTileLocation(nextFirstTile));
        this.tileLocation.put(this.corner.getSecondTile(), piece.getTileLocation(nextSecondTile));
        this.tileLocation.put(this.corner.getThirdTile(), piece.getTileLocation(nextThirdTile));
    }

    public void turnUp(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getUp();
        Tile nextSecondTile = this.corner.getSecondTile().getUp();
        Tile nextThirdTile = this.corner.getThirdTile().getUp();
        this.tileLocation.put(this.corner.getFirstTile(), piece.getTileLocation(nextFirstTile));
        this.tileLocation.put(this.corner.getSecondTile(), piece.getTileLocation(nextSecondTile));
        this.tileLocation.put(this.corner.getThirdTile(), piece.getTileLocation(nextThirdTile));
    }

    public void turnDown(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getDown();
        Tile nextSecondTile = this.corner.getSecondTile().getDown();
        Tile nextThirdTile = this.corner.getThirdTile().getDown();
        this.tileLocation.put(this.corner.getFirstTile(), piece.getTileLocation(nextFirstTile));
        this.tileLocation.put(this.corner.getSecondTile(), piece.getTileLocation(nextSecondTile));
        this.tileLocation.put(this.corner.getThirdTile(), piece.getTileLocation(nextThirdTile));
    }

    public void turnFront(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getFront();
        Tile nextSecondTile = this.corner.getSecondTile().getFront();
        Tile nextThirdTile = this.corner.getThirdTile().getFront();
        this.tileLocation.put(this.corner.getFirstTile(), piece.getTileLocation(nextFirstTile));
        this.tileLocation.put(this.corner.getSecondTile(), piece.getTileLocation(nextSecondTile));
        this.tileLocation.put(this.corner.getThirdTile(), piece.getTileLocation(nextThirdTile));
    }

    public void turnBack(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getBack();
        Tile nextSecondTile = this.corner.getSecondTile().getBack();
        Tile nextThirdTile = this.corner.getThirdTile().getBack();
        this.tileLocation.put(this.corner.getFirstTile(), piece.getTileLocation(nextFirstTile));
        this.tileLocation.put(this.corner.getSecondTile(), piece.getTileLocation(nextSecondTile));
        this.tileLocation.put(this.corner.getThirdTile(), piece.getTileLocation(nextThirdTile));
    }
}
