package ca.mcmaster.se2aa4.island.team09.Drone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Coordinate {
    private final Logger logger = LogManager.getLogger();
    private int xCoordinate;
    private int yCoordinate;
    private int[][] positionAdjustments = {{0,-1}, {1,0}, {0, 1}, {-1, 0}};  // array to store the corresponding adjustments for each direction

    public Coordinate(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public int getYCoordinate() {
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
        logger.trace("Updated coordinates: [{}, {}]", xCoordinate, yCoordinate);
    }
}