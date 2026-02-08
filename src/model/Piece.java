package model;

import java.util.HashMap;
import java.util.Map;

public abstract class Piece {
    protected final Map<Tile, Tile> currentTileLocation;
    protected final Map<Tile, Tile> previousTileLocation;


    protected Piece() {
        this.currentTileLocation = new HashMap<>();
        this.previousTileLocation = new HashMap<>();
    }

    public Tile getCurrentTileLocation(Tile tile) {
        return this.currentTileLocation.get(tile);
    }

    public Tile getPreviousTileLocation(Tile tile) {
        return this.previousTileLocation.get(tile);
    }

    public void setTileLocation(Tile tile, Tile location) {
        this.previousTileLocation.put(tile, this.currentTileLocation.get(tile));
        this.currentTileLocation.put(tile, location);
    }


    public abstract void turnLeft(Piece piece);

    public abstract void turnRight(Piece piece);

    public abstract void turnUp(Piece piece);

    public abstract void turnDown(Piece piece);

    public abstract void turnFront(Piece piece);

    public abstract void turnBack(Piece piece);

}
