package ca.mcmaster.se2aa4.island.team09;

public class GPS {
    private Direction direction;
    private Coordinate coordinates;
    
    public GPS(Direction direction, Coordinate coordinates) {
        this.direction = direction;
        this.coordinates = coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getDirectionOrdinal() {
        return direction.ordinal();  // gets the corresponding direction value for calculations
    }

    public Direction getRightDirection() {
        // computes the direction to the right based on the directions ordinal in the direction enum
        int rightOrdinal = (direction.ordinal() + 1) % 4;
        return Direction.values()[rightOrdinal];
    }

    public Direction getLeftDirection() {
        int leftOrdinal = (direction.ordinal() + 3) % 4;
        return Direction.values()[leftOrdinal];
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void updateCoordinates() {
        coordinates.adjustCoordinate(direction);
    }

    public int getXCord() {
        return coordinates.getXCoordinate();
    }

    public int getYCord() {
        return coordinates.getYCoordinate();
    }
}