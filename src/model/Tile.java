package model;

public enum Tile {
    A(A, A, A, A, A, A),
    B(A, A, A, A, A, A),
    C(A, A, A, A, A, A),
    D(A, A, A, A, A, A),
    E(A, A, A, A, A, A),
    F(A, A, A, A, A, A),
    G(A, A, A, A, A, A),
    H(A, A, A, A, A, A),
    I(A, A, A, A, A, A),
    J(A, A, A, A, A, A),
    K(A, A, A, A, A, A),
    L(A, A, A, A, A, A),
    M(A, A, A, A, A, A),
    N(A, A, A, A, A, A),
    O(A, A, A, A, A, A),
    P(A, A, A, A, A, A),
    Q(A, A, A, A, A, A),
    R(A, A, A, A, A, A),
    S(A, A, A, A, A, A),
    T(A, A, A, A, A, A),
    U(A, A, A, A, A, A),
    V(A, A, A, A, A, A),
    W(A, A, A, A, A, A),
    X(A, A, A, A, A, A),
    a(A, A, A, A, A, A),
    b(A, A, A, A, A, A),
    c(A, A, A, A, A, A),
    d(A, A, A, A, A, A),
    e(A, A, A, A, A, A),
    f(A, A, A, A, A, A),
    g(A, A, A, A, A, A),
    h(A, A, A, A, A, A),
    i(A, A, A, A, A, A),
    j(A, A, A, A, A, A),
    k(A, A, A, A, A, A),
    l(A, A, A, A, A, A),
    m(A, A, A, A, A, A),
    n(A, A, A, A, A, A),
    o(A, A, A, A, A, A),
    p(A, A, A, A, A, A),
    q(A, A, A, A, A, A),
    r(A, A, A, A, A, A),
    s(A, A, A, A, A, A),
    t(A, A, A, A, A, A),
    u(A, A, A, A, A, A),
    v(A, A, A, A, A, A),
    w(A, A, A, A, A, A),
    x(A, A, A, A, A, A);

    private final Tile left;
    private final Tile right;
    private final Tile up;
    private final Tile down;
    private final Tile front;
    private final Tile back;

    Tile(Tile left, Tile right, Tile up, Tile down, Tile front, Tile back) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.front = front;
        this.back = back;
    }


    public Tile getLeft() {
        return this.left;
    }

    public Tile getRight() {
        return this.right;
    }

    public Tile getUp() {
        return this.up;
    }

    public Tile getDown() {
        return this.down;
    }

    public Tile getFront() {
        return front;
    }

    public Tile getBack() {
        return back;
    }
}
