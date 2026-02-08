package view;

import model.Corner;
import model.CornerPiece;
import model.CubeState;
import model.Edge;
import model.EdgePiece;
import model.Piece;
import model.Tile;

import java.util.EnumMap;
import java.util.Map;

public class CubeNetRenderer {
    private static final String COLOR_WHITE = "#FFFFFF";
    private static final String COLOR_YELLOW = "#FFFF00";
    private static final String COLOR_BLUE = "#0000FF";
    private static final String COLOR_GREEN = "#008000";
    private static final String COLOR_RED = "#FF0000";
    private static final String COLOR_ORANGE = "#FFA500";
    private static final String COLOR_UNKNOWN = "#999999";

    private static final Tile[][] FACE_UP = {
            {Tile.A, Tile.a, Tile.B},
            {Tile.d, null, Tile.b},
            {Tile.D, Tile.c, Tile.C}
    };

    private static final Tile[][] FACE_LEFT = {
            {Tile.E, Tile.e, Tile.F},
            {Tile.h, null, Tile.f},
            {Tile.H, Tile.g, Tile.G}
    };

    private static final Tile[][] FACE_FRONT = {
            {Tile.I, Tile.i, Tile.J},
            {Tile.l, null, Tile.j},
            {Tile.L, Tile.k, Tile.K}
    };

    private static final Tile[][] FACE_RIGHT = {
            {Tile.M, Tile.m, Tile.N},
            {Tile.p, null, Tile.n},
            {Tile.P, Tile.o, Tile.O}
    };

    private static final Tile[][] FACE_BACK = {
            {Tile.Q, Tile.q, Tile.R},
            {Tile.t, null, Tile.r},
            {Tile.T, Tile.s, Tile.S}
    };

    private static final Tile[][] FACE_DOWN = {
            {Tile.U, Tile.u, Tile.V},
            {Tile.x, null, Tile.v},
            {Tile.X, Tile.w, Tile.W}
    };

    public String render(CubeState cubeState) {
        if (cubeState == null) {
            return "";
        }
        Map<Tile, String> colors = buildColorMap(cubeState);
        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"cube-net\">");
        builder.append(renderFace("face-up", FACE_UP, COLOR_WHITE, colors));
        builder.append(renderFace("face-left", FACE_LEFT, COLOR_ORANGE, colors));
        builder.append(renderFace("face-front", FACE_FRONT, COLOR_GREEN, colors));
        builder.append(renderFace("face-right", FACE_RIGHT, COLOR_RED, colors));
        builder.append(renderFace("face-back", FACE_BACK, COLOR_BLUE, colors));
        builder.append(renderFace("face-down", FACE_DOWN, COLOR_YELLOW, colors));
        builder.append("</div>");
        return builder.toString();
    }

    private Map<Tile, String> buildColorMap(CubeState cubeState) {
        Map<Tile, String> colors = new EnumMap<>(Tile.class);
        for (Map.Entry<CornerPiece, Corner> entry : cubeState.getCorners().entrySet()) {
            CornerPiece piece = entry.getKey();
            Corner corner = entry.getValue();
            addTileColor(colors, piece.getFirstTile(), corner);
            addTileColor(colors, piece.getSecondTile(), corner);
            addTileColor(colors, piece.getThirdTile(), corner);
        }
        for (Map.Entry<EdgePiece, Edge> entry : cubeState.getEdges().entrySet()) {
            EdgePiece piece = entry.getKey();
            Edge edge = entry.getValue();
            addTileColor(colors, piece.getFirstTile(), edge);
            addTileColor(colors, piece.getSecondTile(), edge);
        }
        return colors;
    }

    private void addTileColor(Map<Tile, String> colors, Tile tile, Piece piece) {
        Tile location = piece.getCurrentTileLocation(tile);
        if (location != null) {
            colors.put(location, tile.getColor());
        }
    }

    private String renderFace(String faceClass, Tile[][] layout, String centerColor, Map<Tile, String> colors) {
        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"face ").append(faceClass).append("\">");
        for (Tile[] row : layout) {
            for (Tile tile : row) {
                String color = tile == null ? centerColor : colors.getOrDefault(tile, COLOR_UNKNOWN);
                String label = tile == null ? "center" : tile.name();
                builder.append("<div class=\"sticker\" style=\"background:")
                        .append(color)
                        .append("\" title=\"")
                        .append(label)
                        .append("\"></div>");
            }
        }
        builder.append("</div>");
        return builder.toString();
    }
}
