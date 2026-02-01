package model;

import java.util.HashMap;
import java.util.Map;

public abstract class Piece {
    protected final Map<Tile, Tile> tileLocation;

    protected Piece() {
        this.tileLocation = new HashMap<>();
    }

    public Tile getTileLocation(Tile tile) {
        return this.tileLocation.get(tile);
    }

    public void setTileLocation(Tile tile, Tile location) {
        this.tileLocation.put(tile, location);
    }


    public abstract void turnLeft(Piece piece);

    public abstract void turnRight(Piece piece);

    public abstract void turnUp(Piece piece);

    public abstract void turnDown(Piece piece);

    public abstract void turnFront(Piece piece);

    public abstract void turnBack(Piece piece);

}
