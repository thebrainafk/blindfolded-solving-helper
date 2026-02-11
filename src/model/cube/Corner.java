package model.cube;

/**
 * Represents the Corner class.
 */
public class Corner extends Piece {
    private final CornerPiece corner;
    private CornerPiece currentLocation;

    /**
     * Creates a new Corner instance.
     */
    public Corner(CornerPiece corner) {
        this.corner = corner;
        this.currentLocation = corner;
        this.initialize();
    }

    /**
     * Creates a new Corner instance.
     */
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

    /**
     * Executes getCurrentLocation.
     */
    public CornerPiece getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Executes getCorner.
     */
    public CornerPiece getCorner() {
        return corner;
    }

    private void swap(Corner corner) {
        this.currentLocation = corner.currentLocation;
    }

    /**
     * Executes turnLeft.
     */
    public void turnLeft(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getLeft();
        Tile nextSecondTile = this.corner.getSecondTile().getLeft();
        Tile nextThirdTile = this.corner.getThirdTile().getLeft();
        this.currentTileLocation.put(this.corner.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.corner.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
        this.currentTileLocation.put(this.corner.getThirdTile(), piece.getCurrentTileLocation(nextThirdTile));
    }

    /**
     * Executes turnRight.
     */
    public void turnRight(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getRight();
        Tile nextSecondTile = this.corner.getSecondTile().getRight();
        Tile nextThirdTile = this.corner.getThirdTile().getRight();
        this.currentTileLocation.put(this.corner.getFirstTile(), piece.currentTileLocation.get(nextFirstTile));
        this.currentTileLocation.put(this.corner.getSecondTile(), piece.currentTileLocation.get(nextSecondTile));
        this.currentTileLocation.put(this.corner.getThirdTile(), piece.currentTileLocation.get(nextThirdTile));
    }

    /**
     * Executes turnUp.
     */
    public void turnUp(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getUp();
        Tile nextSecondTile = this.corner.getSecondTile().getUp();
        Tile nextThirdTile = this.corner.getThirdTile().getUp();
        this.currentTileLocation.put(this.corner.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.corner.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
        this.currentTileLocation.put(this.corner.getThirdTile(), piece.getCurrentTileLocation(nextThirdTile));
    }

    /**
     * Executes turnDown.
     */
    public void turnDown(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getDown();
        Tile nextSecondTile = this.corner.getSecondTile().getDown();
        Tile nextThirdTile = this.corner.getThirdTile().getDown();
        this.currentTileLocation.put(this.corner.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.corner.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
        this.currentTileLocation.put(this.corner.getThirdTile(), piece.getCurrentTileLocation(nextThirdTile));
    }

    /**
     * Executes turnFront.
     */
    public void turnFront(Piece piece) {
        this.swap((Corner) piece);
        Tile nextFirstTile = this.corner.getFirstTile().getFront();
        Tile nextSecondTile = this.corner.getSecondTile().getFront();
        Tile nextThirdTile = this.corner.getThirdTile().getFront();
        this.currentTileLocation.put(this.corner.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.corner.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
        this.currentTileLocation.put(this.corner.getThirdTile(), piece.getCurrentTileLocation(nextThirdTile));
    }

    /**
     * Executes turnBack.
     */
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
