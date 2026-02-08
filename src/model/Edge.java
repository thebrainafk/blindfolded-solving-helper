package model;


public class Edge extends Piece {
    private final EdgePiece edge;
    private EdgePiece currentLocation;
    private EdgePiece previousLocation;

    public Edge(EdgePiece edge) {
        this.edge = edge;
        this.currentLocation = edge;
        this.previousLocation = edge;
        this.initialize();
    }

    private void initialize() {
        this.currentTileLocation.put(edge.getFirstTile(), edge.getFirstTile());
        this.currentTileLocation.put(edge.getSecondTile(), edge.getSecondTile());
        this.previousTileLocation.put(edge.getFirstTile(), edge.getFirstTile());
        this.previousTileLocation.put(edge.getSecondTile(), edge.getSecondTile());
    }

    public EdgePiece getCurrentLocation() {
        return currentLocation;
    }

    public EdgePiece getPreviousLocation() {
        return previousLocation;
    }

    public EdgePiece getEdge() {
        return edge;
    }

    private void swap(Edge edge) {
        this.previousLocation = this.currentLocation;
        this.currentLocation = edge.getPreviousLocation();
    }

    public void turnLeft(Piece piece) {
        this.swap((Edge) piece);
        Tile nextFirstTile = this.edge.getFirstTile().getLeft();
        Tile nextSecondTile = this.edge.getSecondTile().getLeft();
        this.currentTileLocation.put(this.edge.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.edge.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
    }


    public void turnRight(Piece piece) {
        this.swap((Edge) piece);
        Tile nextFirstTile = this.edge.getFirstTile().getRight();
        Tile nextSecondTile = this.edge.getSecondTile().getRight();
        this.setTileLocation(this.edge.getFirstTile(), piece.getPreviousTileLocation(nextFirstTile));
        this.setTileLocation(this.edge.getSecondTile(), piece.getPreviousTileLocation(nextSecondTile));
    }


    public void turnUp(Piece piece) {
        this.swap((Edge) piece);
        Tile nextFirstTile = this.edge.getFirstTile().getUp();
        Tile nextSecondTile = this.edge.getSecondTile().getUp();
        this.currentTileLocation.put(this.edge.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.edge.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
    }


    public void turnDown(Piece piece) {
        this.swap((Edge) piece);
        Tile nextFirstTile = this.edge.getFirstTile().getDown();
        Tile nextSecondTile = this.edge.getSecondTile().getDown();
        this.currentTileLocation.put(this.edge.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.edge.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
    }

    public void turnFront(Piece piece) {
        this.swap((Edge) piece);
        Tile nextFirstTile = this.edge.getFirstTile().getFront();
        Tile nextSecondTile = this.edge.getSecondTile().getFront();
        this.currentTileLocation.put(this.edge.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.edge.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
    }

    public void turnBack(Piece piece) {
        this.swap((Edge) piece);
        Tile nextFirstTile = this.edge.getFirstTile().getBack();
        Tile nextSecondTile = this.edge.getSecondTile().getBack();
        this.currentTileLocation.put(this.edge.getFirstTile(), piece.getCurrentTileLocation(nextFirstTile));
        this.currentTileLocation.put(this.edge.getSecondTile(), piece.getCurrentTileLocation(nextSecondTile));
    }


}
