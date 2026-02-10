package model;

import model.cube.CornerPiece;
import model.cube.EdgePiece;
import model.cube.Tile;
import resources.MemoryWordTable;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateTranslation {

    private final StringBuilder builder;
    private final List<Tile> allTiles;
    private final CubeState cubeState;
    private final Tile buffer;
    private final boolean memoryHelper;
    private MemoryWordTable memoryWordTable;
    private Tile currentTile;
    private Tile currentBuffer;

    public GenerateTranslation(CubeState cubeState, boolean memoryHelper, Tile buffer) throws GameArgumentException {
        this.builder = new StringBuilder();
        this.allTiles = new ArrayList<>(List.of(Tile.values()));
        this.removeTileFromList(buffer);
        this.cubeState = cubeState;
        this.memoryHelper = memoryHelper;
        this.currentTile = buffer;
        this.buffer = buffer;
        this.currentBuffer = buffer;
        this.memoryWordTable = null;
        if (memoryHelper) {
            this.initializeMemoryWordTable();
        }
    }

    private void initializeMemoryWordTable() throws GameArgumentException {
        try (InputStream inputStream = MemoryWordTable.class
                .getClassLoader()
                .getResourceAsStream("resources/memory_words.txt")) {
            if (inputStream == null) {
                throw new GameArgumentException("memory_words.txt not found");
            }
            this.memoryWordTable = new MemoryWordTable(inputStream);
        } catch (IOException error) {
            throw new GameArgumentException("Unable to load memory_words.txt: " + error.getMessage());
        }

    }

    public String translateEdgesM2(List<Tile> tileSequence) {
        for (int i = 0; i < tileSequence.size(); i = i + 2) {
            Tile firstTile = tileSequence.get(i);
            if (i + 1 < tileSequence.size()) {
                Tile secondTile = tileSequence.get(i + 1);

                Tile opposite = secondTile.getOppositeTile();
                if (opposite != null) {
                    secondTile = opposite;
                }

                this.appendTileString(firstTile, secondTile);
            } else {
                builder.append(firstTile);
            }
            builder.append(" ");
        }
        return builder.toString();
    }

    public String translatePochmann(List<Tile> tileSequence) {
        for (int i = 0; i < tileSequence.size(); i = i + 2) {
            Tile firstTile = tileSequence.get(i);
            if (i + 1 < tileSequence.size()) {
                Tile secondTile = tileSequence.get(i + 1);

                appendTileString(firstTile, secondTile);
            } else {
                builder.append(firstTile);
            }
            builder.append(" ");
        }

        return builder.toString();
    }

    private void appendTileString(Tile firstTile, Tile secondTile) {
        if (this.memoryHelper) {
            int[] coordinates = firstTile.getCoordinates(secondTile);
            String word = this.memoryWordTable.getWord(coordinates[0], coordinates[1]);
            builder.append(word).append("(").append(firstTile).append(secondTile).append(")");
        } else {
            builder.append(firstTile);
            builder.append(secondTile);
        }
    }

    public List<Tile> generateTileSequence() {
        List<Tile> tileSequence = new ArrayList<>();

        while (!this.isBufferReached()) {
            Tile location = this.currentTile.getLocation(this.cubeState);

            tileSequence.addLast(location);
            this.removeTileFromList(location);
            this.currentTile = location;
        }

        while (!allTiles.isEmpty()) {
            this.setNewBuffer();
            if (!isNewBufferValid()) {
                continue;
            }
            tileSequence.addLast(currentTile);

            Tile location;
            do {
                location = this.currentTile.getLocation(this.cubeState);

                tileSequence.addLast(location);
                this.removeTileFromList(location);
                this.currentTile = location;
            } while (!this.isCurrentBufferReached());
        }
        return tileSequence;
    }

    private void removeTileFromList(Tile tile) {
        CornerPiece bufferCorner = CornerPiece.getPieceFromTile(tile);
        EdgePiece bufferEdge = EdgePiece.getPieceFromTile(tile);

        if (bufferCorner != null) {
            this.allTiles.remove(bufferCorner.getFirstTile());
            this.allTiles.remove(bufferCorner.getSecondTile());
            this.allTiles.remove(bufferCorner.getThirdTile());
        }
        if (bufferEdge != null) {
            this.allTiles.remove(bufferEdge.getFirstTile());
            this.allTiles.remove(bufferEdge.getSecondTile());
        }
    }

    private boolean isNewBufferValid() {
        Tile location = this.currentTile.getLocation(this.cubeState);
        if (location.equals(this.currentTile)) {
            return false;
        }
        boolean isBufferUppercase = Character.isUpperCase(buffer.toString().charAt(0));
        boolean isCurrentTileUppercase = Character.isUpperCase(currentTile.toString().charAt(0));

        return isBufferUppercase && isCurrentTileUppercase || !isBufferUppercase && !isCurrentTileUppercase;
    }

    private void setNewBuffer() {
        Collections.shuffle(this.allTiles);
        this.currentBuffer = this.allTiles.removeFirst();
        this.currentTile = currentBuffer;
    }

    private boolean isCurrentBufferReached() {
        CornerPiece bufferCorner = CornerPiece.getPieceFromTile(this.currentBuffer);
        EdgePiece bufferEdge = EdgePiece.getPieceFromTile(this.currentBuffer);
        CornerPiece currentCorner = CornerPiece.getPieceFromTile(this.currentTile);
        EdgePiece currentEdge = EdgePiece.getPieceFromTile(this.currentTile);

        if (bufferCorner != null && currentCorner != null) {
            return bufferCorner.equals(currentCorner);
        }
        if (bufferEdge != null && currentEdge != null) {
            return bufferEdge.equals(currentEdge);
        }
        return false;
    }

    private boolean isBufferReached() {
        CornerPiece bufferCorner = CornerPiece.getPieceFromTile(this.buffer);
        EdgePiece bufferEdge = EdgePiece.getPieceFromTile(this.buffer);
        CornerPiece currentCorner = CornerPiece.getPieceFromTile(this.currentTile.getLocation(cubeState));
        EdgePiece currentEdge = EdgePiece.getPieceFromTile(this.currentTile.getLocation(cubeState));

        if (bufferCorner != null && currentCorner != null) {
            return bufferCorner.equals(currentCorner);
        }
        if (bufferEdge != null && currentEdge != null) {
            return bufferEdge.equals(currentEdge);
        }
        return false;
    }
}
