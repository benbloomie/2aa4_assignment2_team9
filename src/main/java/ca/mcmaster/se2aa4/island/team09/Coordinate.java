package ca.mcmaster.se2aa4.island.team09;

public class Coordinate {
    private double xCoordinate;
    private double yCoordinate;
    private int[][] positionAdjustments = {{0,1}, {1,0}, {0, -1}, {-1, 0}};  // array to store the corresponding adjustmnets

    public Coordinate(double x, double y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public double getXCoordinate() {
        return this.xCoordinate;
    }

    public double getYCoordinate() {
        return this.yCoordinate;
    }

    public void adjustCoordinate(Direction direction) {
        int directionValue = direction.ordinal();
        // adjust the position using its corresponding ordinal value to index into the array
        int xAdjust = positionAdjustments[directionValue][0];
        this.xCoordinate = xCoordinate + xAdjust;
        // increments the position using the adjustment factor from the array
        int yAdjust = positionAdjustments[directionValue][1];
        this.yCoordinate = yCoordinate + yAdjust;
    }
}