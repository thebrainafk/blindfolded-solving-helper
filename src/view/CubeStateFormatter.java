package view;

import model.Corner;
import model.CornerPiece;
import model.CubeState;
import model.Edge;
import model.EdgePiece;
import model.Piece;
import model.Tile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Formats cube state for display in the web UI.
 */
public class CubeStateFormatter {
    public String format(CubeState cubeState) {
        StringBuilder builder = new StringBuilder();
        appendCorners(builder, cubeState.getCorners());
        builder.append(System.lineSeparator());
        appendEdges(builder, cubeState.getEdges());
        return builder.toString().trim();
    }

    private void appendCorners(StringBuilder builder, Map<CornerPiece, Corner> corners) {
        builder.append("Corners").append(System.lineSeparator());
        for (CornerPiece piece : sortedKeys(corners)) {
            Corner corner = corners.get(piece);
            builder.append(piece.name())
                    .append(": (")
                    .append(corner.getCurrentLocation().name())
                    .append(") ")
                    .append(formatCornerTiles(piece, corner))
                    .append(System.lineSeparator());
        }
    }

    private void appendEdges(StringBuilder builder, Map<EdgePiece, Edge> edges) {
        builder.append("Edges").append(System.lineSeparator());
        for (EdgePiece piece : sortedKeys(edges)) {
            Edge edge = edges.get(piece);
            builder.append(piece.name())
                    .append(": (")
                    .append(edge.getCurrentLocation().name())
                    .append(") ")
                    .append(formatEdgeTiles(piece, edge))
                    .append(System.lineSeparator());
        }
    }

    private String formatCornerTiles(CornerPiece piece, Corner corner) {
        return formatTileMapping(piece.getFirstTile(), corner)
                + ", " + formatTileMapping(piece.getSecondTile(), corner)
                + ", " + formatTileMapping(piece.getThirdTile(), corner);
    }

    private String formatEdgeTiles(EdgePiece piece, Edge edge) {
        return formatTileMapping(piece.getFirstTile(), edge)
                + ", " + formatTileMapping(piece.getSecondTile(), edge);
    }

    private String formatTileMapping(Tile tile, Piece piece) {
        Tile location = piece.getCurrentTileLocation(tile);
        return tile.name() + ": " + (location == null ? "?" : location.name());
    }

    private <T extends Enum<T>, V> List<T> sortedKeys(Map<T, V> map) {
        List<T> keys = new ArrayList<>(map.keySet());
        keys.sort(Comparator.comparing(Enum::name));
        return keys;
    }
}
