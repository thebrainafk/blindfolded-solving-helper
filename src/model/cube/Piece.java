package model.cube;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the Piece class.
 */
public abstract class Piece {
    protected final Map<Tile, Tile> currentTileLocation;

    protected Piece() {
        this.currentTileLocation = new HashMap<>();
    }

    /**
     * Executes getCurrentTileLocation.
     */
    public Tile getCurrentTileLocation(Tile tile) {
        return this.currentTileLocation.get(tile);
    }

    public abstract void turnLeft(Piece piece);

    public abstract void turnRight(Piece piece);

    public abstract void turnUp(Piece piece);

    public abstract void turnDown(Piece piece);

    public abstract void turnFront(Piece piece);

    public abstract void turnBack(Piece piece);

}
