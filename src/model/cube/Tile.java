package model.cube;

import model.CubeState;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the Tile enum.
 */
public enum Tile {
    A(Constants.WHITE, 0, 0),
    B(Constants.WHITE, 1, 1),
    C(Constants.WHITE, 2, 2),
    D(Constants.WHITE, 3, 3),
    E(Constants.ORANGE, 4, 4),
    F(Constants.ORANGE, 5, 5),
    G(Constants.ORANGE, 6, 6),
    H(Constants.ORANGE, 7, 7),
    I(Constants.GREEN, 8, 8),
    J(Constants.GREEN, 9, 9),
    K(Constants.GREEN, 10, 10),
    L(Constants.GREEN, 11, 11),
    M(Constants.RED, 12, 12),
    N(Constants.RED, 13, 13),
    O(Constants.RED, 14, 14),
    P(Constants.RED, 15, 15),
    Q(Constants.BLUE, 16, 16),
    R(Constants.BLUE, 17, 17),
    S(Constants.BLUE, 18, 18),
    T(Constants.BLUE, 19, 19),
    U(Constants.YELLOW, 20, 20),
    V(Constants.YELLOW, 21, 21),
    W(Constants.YELLOW, 22, 22),
    X(Constants.YELLOW, 23, 23),
    a(Constants.WHITE, 0, 0),
    b(Constants.WHITE, 1, 1),
    c(Constants.WHITE, 2, 2),
    d(Constants.WHITE, 3, 3),
    e(Constants.ORANGE, 4, 4),
    f(Constants.ORANGE, 5, 5),
    g(Constants.ORANGE, 6, 6),
    h(Constants.ORANGE, 7, 7),
    i(Constants.GREEN, 8, 8),
    j(Constants.GREEN, 9, 9),
    k(Constants.GREEN, 10, 10),
    l(Constants.GREEN, 11, 11),
    m(Constants.RED, 12, 12),
    n(Constants.RED, 13, 13),
    o(Constants.RED, 14, 14),
    p(Constants.RED, 15, 15),
    q(Constants.BLUE, 16, 16),
    r(Constants.BLUE, 17, 17),
    s(Constants.BLUE, 18, 18),
    t(Constants.BLUE, 19, 19),
    u(Constants.YELLOW, 20, 20),
    v(Constants.YELLOW, 21, 21),
    w(Constants.YELLOW, 22, 22),
    x(Constants.YELLOW, 23, 23);

    private final String color;
    private final int row;
    private final int column;

    private static final Map<Tile, Tile> left = new HashMap<>();
    private static final Map<Tile, Tile> right = new HashMap<>();
    private static final Map<Tile, Tile> up = new HashMap<>();
    private static final Map<Tile, Tile> down = new HashMap<>();
    private static final Map<Tile, Tile> front = new HashMap<>();
    private static final Map<Tile, Tile> back = new HashMap<>();


    Tile(String color, int row, int column) {
        this.color = color;
        this.row = row;
        this.column = column;
    }

    /**
     * Executes getCoordinates.
     */
    public int[] getCoordinates(Tile tile) {
        return new int[]{this.row, tile.column};
    }

    static {
        fillMaps();
    }

    private static void fillMaps() {
        left.put(R, X);
        left.put(E, H);
        left.put(A, S);
        left.put(D, R);
        left.put(F, E);
        left.put(I, A);
        left.put(L, D);
        left.put(G, F);
        left.put(U, I);
        left.put(X, L);
        left.put(H, G);
        left.put(S, U);

        left.put(e, h);
        left.put(d, r);
        left.put(f, e);
        left.put(l, d);
        left.put(g, f);
        left.put(x, l);
        left.put(h, g);
        left.put(r, x);


        right.put(C, K);
        right.put(M, P);
        right.put(J, V);
        right.put(K, W);
        right.put(P, O);
        right.put(V, T);
        right.put(W, Q);
        right.put(O, N);
        right.put(T, B);
        right.put(Q, C);
        right.put(N, M);
        right.put(B, J);

        right.put(b, j);
        right.put(m, p);
        right.put(j, v);
        right.put(p, o);
        right.put(v, t);
        right.put(o, n);
        right.put(t, b);
        right.put(n, m);


        up.put(F, J);
        up.put(D, C);
        up.put(I, M);
        up.put(J, N);
        up.put(C, B);
        up.put(M, Q);
        up.put(N, R);
        up.put(B, A);
        up.put(Q, E);
        up.put(R, F);
        up.put(A, D);
        up.put(E, I);

        up.put(c, b);
        up.put(i, m);
        up.put(b, a);
        up.put(m, q);
        up.put(a, d);
        up.put(q, e);
        up.put(d, c);
        up.put(e, i);


        down.put(H, T);
        down.put(X, W);
        down.put(S, O);
        down.put(T, P);
        down.put(W, V);
        down.put(O, K);
        down.put(P, L);
        down.put(V, U);
        down.put(K, G);
        down.put(L, H);
        down.put(U, X);
        down.put(G, S);

        down.put(w, v);
        down.put(s, o);
        down.put(v, u);
        down.put(o, k);
        down.put(u, x);
        down.put(k, g);
        down.put(x, w);
        down.put(g, s);


        front.put(G, V);
        front.put(L, K);
        front.put(U, P);
        front.put(V, M);
        front.put(K, J);
        front.put(P, C);
        front.put(M, D);
        front.put(J, I);
        front.put(C, F);
        front.put(D, G);
        front.put(I, L);
        front.put(F, U);

        front.put(k, j);
        front.put(u, p);
        front.put(j, i);
        front.put(p, c);
        front.put(i, l);
        front.put(c, f);
        front.put(l, k);
        front.put(f, u);


        back.put(O, X);
        back.put(T, S);
        back.put(W, H);
        back.put(X, E);
        back.put(S, R);
        back.put(H, A);
        back.put(E, B);
        back.put(R, Q);
        back.put(A, N);
        back.put(B, O);
        back.put(Q, T);
        back.put(N, W);

        back.put(s, r);
        back.put(w, h);
        back.put(r, q);
        back.put(h, a);
        back.put(q, t);
        back.put(a, n);
        back.put(t, s);
        back.put(n, w);
    }

    /**
     * Executes getLeft.
     */
    public Tile getLeft() {
        return left.get(this);
    }

    /**
     * Executes getRight.
     */
    public Tile getRight() {
        return right.get(this);
    }

    /**
     * Executes getUp.
     */
    public Tile getUp() {
        return up.get(this);
    }

    /**
     * Executes getDown.
     */
    public Tile getDown() {
        return down.get(this);
    }

    /**
     * Executes getFront.
     */
    public Tile getFront() {
        return front.get(this);
    }

    /**
     * Executes getBack.
     */
    public Tile getBack() {
        return back.get(this);
    }

    /**
     * Executes getColor.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Executes getLocation.
     */
    public Tile getLocation(CubeState cubeState) {
        Tile location = null;
        CornerPiece cornerFromTile = CornerPiece.getPieceFromTile(this);
        EdgePiece edgeFromTile = EdgePiece.getPieceFromTile(this);
        if (cornerFromTile != null) {
            Corner corner = cubeState.getCorners().get(cornerFromTile);
            location = corner.getCurrentTileLocation(this);
        }
        if (edgeFromTile != null) {
            Edge edge = cubeState.getEdges().get(edgeFromTile);
            location = edge.getCurrentTileLocation(this);
        }

        return location;
    }

    /**
     * Executes getOppositeTile.
     */
    public Tile getOppositeTile() {
        switch (this) {
            case c -> {
                return w;
            }
            case w -> {
                return c;
            }
            case i -> {
                return s;
            }
            case s -> {
                return i;
            }
            default -> {
                return null;
            }
        }
    }

    private static class Constants {
        private static final String WHITE = "#FFFFFF";
        private static final String YELLOW = "#FFFF00";
        private static final String BLUE = "#3A5FCD";
        private static final String GREEN = "#008000";
        private static final String RED = "#FF0000";
        private static final String ORANGE = "#FFA500";
    }
}
