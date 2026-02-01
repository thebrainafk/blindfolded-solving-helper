package model;


public class Edge extends Piece {
    private final EdgePiece edge;
    private EdgePiece location;

    public Edge(EdgePiece edge) {
        this.edge = edge;
        this.location = edge;
        this.initialize();
    }

    private void initialize() {
        this.tileLocation.put(edge.getFirstTile(), edge.getFirstTile());
        this.tileLocation.put(edge.getSecondTile(), edge.getSecondTile());
    }

    public EdgePiece getLocation() {
        return location;
    }

    public EdgePiece getEdge() {
        return edge;
    }

    private void swap(Edge edge) {
        this.location = edge.getLocation();
    }

    public void turnLeft(Piece piece) {
        this.swap((Edge) piece);
        Tile nextFirstTile = this.edge.getFirstTile().getLeft();
        Tile nextSecondTile = this.edge.getSecondTile().getLeft();
        this.tileLocation.put(this.edge.getFirstTile(), piece.getTileLocation(nextFirstTile));
        this.tileLocation.put(this.edge.getSecondTile(), piece.getTileLocation(nextSecondTile));
    }


    public void turnRight(Piece piece) {
        this.swap((Edge) piece);
        Tile nextFirstTile = this.edge.getFirstTile().getRight();
        Tile nextSecondTile = this.edge.getSecondTile().getRight();
        this.tileLocation.put(this.edge.getFirstTile(), piece.getTileLocation(nextFirstTile));
        this.tileLocation.put(this.edge.getSecondTile(), piece.getTileLocation(nextSecondTile));
    }


    public void turnUp(Piece piece) {
        this.swap((Edge) piece);
        Tile nextFirstTile = this.edge.getFirstTile().getUp();
        Tile nextSecondTile = this.edge.getSecondTile().getUp();
        this.tileLocation.put(this.edge.getFirstTile(), piece.getTileLocation(nextFirstTile));
        this.tileLocation.put(this.edge.getSecondTile(), piece.getTileLocation(nextSecondTile));
    }


    public void turnDown(Piece piece) {
        this.swap((Edge) piece);
        Tile nextFirstTile = this.edge.getFirstTile().getDown();
        Tile nextSecondTile = this.edge.getSecondTile().getDown();
        this.tileLocation.put(this.edge.getFirstTile(), piece.getTileLocation(nextFirstTile));
        this.tileLocation.put(this.edge.getSecondTile(), piece.getTileLocation(nextSecondTile));
    }

    public void turnFront(Piece piece) {
        this.swap((Edge) piece);
        Tile nextFirstTile = this.edge.getFirstTile().getFront();
        Tile nextSecondTile = this.edge.getSecondTile().getFront();
        this.tileLocation.put(this.edge.getFirstTile(), piece.getTileLocation(nextFirstTile));
        this.tileLocation.put(this.edge.getSecondTile(), piece.getTileLocation(nextSecondTile));
    }

    public  void turnBack(Piece piece) {
        this.swap((Edge) piece);
        Tile nextFirstTile = this.edge.getFirstTile().getBack();
        Tile nextSecondTile = this.edge.getSecondTile().getBack();
        this.tileLocation.put(this.edge.getFirstTile(), piece.getTileLocation(nextFirstTile));
        this.tileLocation.put(this.edge.getSecondTile(), piece.getTileLocation(nextSecondTile));
    }
}
