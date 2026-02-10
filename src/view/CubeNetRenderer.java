package view;

import model.CubeState;
import model.cube.Tile;

public class CubeNetRenderer {
    private static final String COLOR_WHITE = "#FFFFFF";
    private static final String COLOR_YELLOW = "#FFFF00";
    private static final String COLOR_BLUE = "#3A5FCD";
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
        return "<div class=\"cube-net\">" +
                renderFace("face-up", FACE_UP, COLOR_WHITE, cubeState) +
                renderFace("face-left", FACE_LEFT, COLOR_ORANGE, cubeState) +
                renderFace("face-front", FACE_FRONT, COLOR_GREEN, cubeState) +
                renderFace("face-right", FACE_RIGHT, COLOR_RED, cubeState) +
                renderFace("face-back", FACE_BACK, COLOR_BLUE, cubeState) +
                renderFace("face-down", FACE_DOWN, COLOR_YELLOW, cubeState) +
                "</div>";
    }

    private String renderFace(String faceClass, Tile[][] layout, String centerColor, CubeState cubeState) {
        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"face ").append(faceClass).append("\">");
        for (Tile[] row : layout) {
            for (Tile tile : row) {
                Tile location = this.getLocation(tile, cubeState);
                String color = tile == null ? centerColor : this.getColor(location);
                String label = tile == null ? "" : this.getName(tile, location);
                builder.append("<div class=\"sticker\" style=\"background:")
                        .append(color)
                        .append("\" title=\"")
                        .append(label)
                        .append("\">");
                if (!label.isEmpty()) {
                    builder.append("<span class=\"sticker-label\">")
                            .append(label)
                            .append("</span>");
                }
                builder.append("</div>");
            }
        }
        builder.append("</div>");
        return builder.toString();
    }

    private Tile getLocation(Tile tile, CubeState cubeState) {
        return tile == null ? null : tile.getLocation(cubeState);
    }

    private String getName(Tile tile, Tile location) {
        return location == null ? "unknown" : tile.name() + "|" + location.name();
    }

    private String getColor(Tile location) {
        return location == null ? COLOR_UNKNOWN : location.getColor();
    }
}
