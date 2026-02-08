package model;

public class Corner extends Piece {
    private final CornerPiece corner;
    private CornerPiece currentLocation;

    public Corner(CornerPiece corner) {
        this.corner = corner;
        this.currentLocation = corner;
        this.initialize();
    }

    public Corner(Corner source) {
        this.corner = source.corner;
        this.currentLocation = source.currentLocation;
        this.currentTileLocation.putAll(source.currentTileLocation);
    }

    private void initialize() {
        this.currentTileLocation.put(corner.getFirstTile(), corner.getFirstTile());
        this.currentTileLocation.put(corner.getSecondTile(), corner.getSecondTile());
        this.currentTileLocation.put(corner.getThirdTile(), corner.getThirdTile());
    }

    public CornerPiece getCurrentLocation() {
        return currentLocation;
    }

    public CornerPiece getCorner() {
        return corner;
    }

    private void swap(Corner corner) {
//        this.previousLocation = this.currentLocation;
        this.currentLocation = corner.currentLocation;
    }

    public void turnLeft(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getLeft();
        Tile nextSecondTile = this.corner.getSecondTile().getLeft();
        Tile nextThirdTile = this.corner.getThirdTile().getLeft();
        this.currentTileLocation.put(this.corner.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.corner.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
        this.currentTileLocation.put(this.corner.getThirdTile(), piece.getCurrentTileLocation(nextThirdTile));
    }

    public void turnRight(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getRight();
        Tile nextSecondTile = this.corner.getSecondTile().getRight();
        Tile nextThirdTile = this.corner.getThirdTile().getRight();
        this.currentTileLocation.put(this.corner.getFirstTile(), piece.currentTileLocation.get(nextFirstTile));
        this.currentTileLocation.put(this.corner.getSecondTile(), piece.currentTileLocation.get(nextSecondTile));
        this.currentTileLocation.put(this.corner.getThirdTile(), piece.currentTileLocation.get(nextThirdTile));
    }

    public void turnUp(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getUp();
        Tile nextSecondTile = this.corner.getSecondTile().getUp();
        Tile nextThirdTile = this.corner.getThirdTile().getUp();
        this.currentTileLocation.put(this.corner.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.corner.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
        this.currentTileLocation.put(this.corner.getThirdTile(), piece.getCurrentTileLocation(nextThirdTile));
    }

    public void turnDown(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getDown();
        Tile nextSecondTile = this.corner.getSecondTile().getDown();
        Tile nextThirdTile = this.corner.getThirdTile().getDown();
        this.currentTileLocation.put(this.corner.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.corner.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
        this.currentTileLocation.put(this.corner.getThirdTile(), piece.getCurrentTileLocation(nextThirdTile));
    }

    public void turnFront(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getFront();
        Tile nextSecondTile = this.corner.getSecondTile().getFront();
        Tile nextThirdTile = this.corner.getThirdTile().getFront();
        this.currentTileLocation.put(this.corner.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.corner.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
        this.currentTileLocation.put(this.corner.getThirdTile(), piece.getCurrentTileLocation(nextThirdTile));
    }

    public void turnBack(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getBack();
        Tile nextSecondTile = this.corner.getSecondTile().getBack();
        Tile nextThirdTile = this.corner.getThirdTile().getBack();
        this.currentTileLocation.put(this.corner.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.corner.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
        this.currentTileLocation.put(this.corner.getThirdTile(), piece.getCurrentTileLocation(nextThirdTile));
    }


}
