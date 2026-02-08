package model;

import java.util.HashMap;
import java.util.Map;

public enum Tile {
    A(Constants.WHITE),
    B(Constants.WHITE),
    C(Constants.WHITE),
    D(Constants.WHITE),
    E(Constants.ORANGE),
    F(Constants.ORANGE),
    G(Constants.ORANGE),
    H(Constants.ORANGE),
    I(Constants.GREEN),
    J(Constants.GREEN),
    K(Constants.GREEN),
    L(Constants.GREEN),
    M(Constants.RED),
    N(Constants.RED),
    O(Constants.RED),
    P(Constants.RED),
    Q(Constants.BLUE),
    R(Constants.BLUE),
    S(Constants.BLUE),
    T(Constants.BLUE),
    U(Constants.YELLOW),
    V(Constants.YELLOW),
    W(Constants.YELLOW),
    X(Constants.YELLOW),
    a(Constants.WHITE),
    b(Constants.WHITE),
    c(Constants.WHITE),
    d(Constants.WHITE),
    e(Constants.ORANGE),
    f(Constants.ORANGE),
    g(Constants.ORANGE),
    h(Constants.ORANGE),
    i(Constants.GREEN),
    j(Constants.GREEN),
    k(Constants.GREEN),
    l(Constants.GREEN),
    m(Constants.RED),
    n(Constants.RED),
    o(Constants.RED),
    p(Constants.RED),
    q(Constants.BLUE),
    r(Constants.BLUE),
    s(Constants.BLUE),
    t(Constants.BLUE),
    u(Constants.YELLOW),
    v(Constants.YELLOW),
    w(Constants.YELLOW),
    x(Constants.YELLOW);

    private final String color;
    private static final Map<Tile, Tile> left = new HashMap<>();
    private static final Map<Tile, Tile> right = new HashMap<>();
    private static final Map<Tile, Tile> up = new HashMap<>();
    private static final Map<Tile, Tile> down = new HashMap<>();
    private static final Map<Tile, Tile> front = new HashMap<>();
    private static final Map<Tile, Tile> back = new HashMap<>();


    Tile(String color) {
        this.color = color;
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

    public Tile getLeft() {
        return left.get(this);
    }

    public Tile getRight() {
        return right.get(this);
    }

    public Tile getUp() {
        return up.get(this);
    }

    public Tile getDown() {
        return down.get(this);
    }

    public Tile getFront() {
        return front.get(this);
    }

    public Tile getBack() {
        return back.get(this);
    }

    public String getColor() {
        return this.color;
    }

    private static class Constants {
        private static final String WHITE = "#FFFFFF";
        private static final String YELLOW = "#FFFF00";
        private static final String BLUE = "#0000FF";
        private static final String GREEN = "#008000";
        private static final String RED = "#FF0000";
        private static final String ORANGE = "#FFA500";
    }
}
