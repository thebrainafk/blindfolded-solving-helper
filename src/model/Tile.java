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
        fillMaps();
    }

    private static void fillMaps() {
        //FIXME Not yet mapped correctly
        left.put(A, B);
        left.put(B, C);
        left.put(C, D);
        left.put(D, A);
        left.put(E, F);
        left.put(F, G);
        left.put(G, H);
        left.put(H, E);
        left.put(I, J);
        left.put(J, K);
        left.put(K, L);
        left.put(L, I);
        left.put(M, N);
        left.put(N, O);
        left.put(O, P);
        left.put(P, M);
        left.put(Q, R);
        left.put(R, S);
        left.put(S, T);
        left.put(T, Q);
        left.put(U, V);
        left.put(V, W);
        left.put(W, X);
        left.put(X, U);
        left.put(a, b);
        left.put(b, c);
        left.put(c, d);
        left.put(d, a);
        left.put(e, f);
        left.put(f, g);
        left.put(g, h);
        left.put(h, e);
        left.put(i, j);
        left.put(j, k);
        left.put(k, l);
        left.put(l, i);
        left.put(m, n);
        left.put(n, o);
        left.put(o, p);
        left.put(p, m);
        left.put(q, r);
        left.put(r, s);
        left.put(s, t);
        left.put(t, q);
        left.put(u, v);
        left.put(v, w);
        left.put(w, x);
        left.put(x, u);

        right.put(A, D);
        right.put(B, C);
        right.put(C, B);
        right.put(D, A);
        right.put(E, H);
        right.put(F, G);
        right.put(G, F);
        right.put(H, E);
        right.put(I, L);
        right.put(J, K);
        right.put(K, J);
        right.put(L, I);
        right.put(M, P);
        right.put(N, O);
        right.put(O, N);
        right.put(P, M);
        right.put(Q, T);
        right.put(R, S);
        right.put(S, R);
        right.put(T, Q);
        right.put(U, X);
        right.put(V, W);
        right.put(W, V);
        right.put(X, U);
        right.put(a, d);
        right.put(b, c);
        right.put(c, b);
        right.put(d, a);
        right.put(e, h);
        right.put(f, g);
        right.put(g, f);
        right.put(h, e);
        right.put(i, l);
        right.put(j, k);
        right.put(k, j);
        right.put(l, i);
        right.put(m, p);
        right.put(n, o);
        right.put(o, n);
        right.put(p, m);
        right.put(q, t);
        right.put(r, s);
        right.put(s, r);
        right.put(t, q);
        right.put(u, x);
        right.put(v, w);
        right.put(w, v);
        right.put(x, u);

        up.put(A, A);
        up.put(B, B);
        up.put(C, C);
        up.put(D, D);
        up.put(E, E);
        up.put(F, F);
        up.put(G, G);
        up.put(H, H);
        up.put(I, I);
        up.put(J, J);
        up.put(K, K);
        up.put(L, L);
        up.put(M, M);
        up.put(N, N);
        up.put(O, O);
        up.put(P, P);
        up.put(Q, Q);
        up.put(R, R);
        up.put(S, S);
        up.put(T, T);
        up.put(U, U);
        up.put(V, V);
        up.put(W, W);
        up.put(X, X);
        up.put(a, a);
        up.put(b, b);
        up.put(c, c);
        up.put(d, d);
        up.put(e, e);
        up.put(f, f);
        up.put(g, g);
        up.put(h, h);
        up.put(i, i);
        up.put(j, j);
        up.put(k, k);
        up.put(l, l);
        up.put(m, m);
        up.put(n, n);
        up.put(o, o);
        up.put(p, p);
        up.put(q, q);
        up.put(r, r);
        up.put(s, s);
        up.put(t, t);
        up.put(u, u);
        up.put(v, v);
        up.put(w, w);
        up.put(x, x);

        down.put(A, X);
        down.put(B, W);
        down.put(C, V);
        down.put(D, U);
        down.put(E, T);
        down.put(F, S);
        down.put(G, R);
        down.put(H, Q);
        down.put(I, O);
        down.put(J, N);
        down.put(K, M);
        down.put(L, L);
        down.put(M, K);
        down.put(N, J);
        down.put(O, I);
        down.put(P, H);
        down.put(Q, G);
        down.put(R, F);
        down.put(S, E);
        down.put(T, D);
        down.put(U, C);
        down.put(V, B);
        down.put(W, A);
        down.put(X, A);
        down.put(a, x);
        down.put(b, w);
        down.put(c, v);
        down.put(d, u);
        down.put(e, t);
        down.put(f, s);
        down.put(g, r);
        down.put(h, q);
        down.put(i, o);
        down.put(j, n);
        down.put(k, m);
        down.put(l, l);
        down.put(m, k);
        down.put(n, j);
        down.put(o, i);
        down.put(p, h);
        down.put(q, g);
        down.put(r, f);
        down.put(s, e);
        down.put(t, d);
        down.put(u, c);
        down.put(v, b);
        down.put(w, a);
        down.put(x, a);

        front.put(A, A);
        front.put(B, B);
        front.put(C, C);
        front.put(D, D);
        front.put(E, E);
        front.put(F, F);
        front.put(G, G);
        front.put(H, H);
        front.put(I, I);
        front.put(J, J);
        front.put(K, K);
        front.put(L, L);
        front.put(M, M);
        front.put(N, N);
        front.put(O, O);
        front.put(P, P);
        front.put(Q, Q);
        front.put(R, R);
        front.put(S, S);
        front.put(T, T);
        front.put(U, U);
        front.put(V, V);
        front.put(W, W);
        front.put(X, X);
        front.put(a, a);
        front.put(b, b);
        front.put(c, c);
        front.put(d, d);
        front.put(e, e);
        front.put(f, f);
        front.put(g, g);
        front.put(h, h);
        front.put(i, i);
        front.put(j, j);
        front.put(k, k);
        front.put(l, l);
        front.put(m, m);
        front.put(n, n);
        front.put(o, o);
        front.put(p, p);
        front.put(q, q);
        front.put(r, r);
        front.put(s, s);
        front.put(t, t);
        front.put(u, u);
        front.put(v, v);
        front.put(w, w);
        front.put(x, x);

        back.put(A, X);
        back.put(B, W);
        back.put(C, V);
        back.put(D, U);
        back.put(E, T);
        back.put(F, S);
        back.put(G, R);
        back.put(H, Q);
        back.put(I, O);
        back.put(J, N);
        back.put(K, M);
        back.put(L, L);
        back.put(M, K);
        back.put(N, J);
        back.put(O, I);
        back.put(P, H);
        back.put(Q, G);
        back.put(R, F);
        back.put(S, E);
        back.put(T, D);
        back.put(U, C);
        back.put(V, B);
        back.put(W, A);
        back.put(X, A);
        back.put(a, x);
        back.put(b, w);
        back.put(c, v);
        back.put(d, u);
        back.put(e, t);
        back.put(f, s);
        back.put(g, r);
        back.put(h, q);
        back.put(i, o);
        back.put(j, n);
        back.put(k, m);
        back.put(l, l);
        back.put(m, k);
        back.put(n, j);
        back.put(o, i);
        back.put(p, h);
        back.put(q, g);
        back.put(r, f);
        back.put(s, e);
        back.put(t, d);
        back.put(u, c);
        back.put(v, b);
        back.put(w, a);
        back.put(x, a);
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
