package ca.mcmaster.se2aa4.island.team09;
import java.util.*;

public class DirectionManager {
    private Direction direction;
    private List<Direction> directions;

    public DirectionManager(Direction direction) {
        this.direction = direction;
        this.directions = direction.getDirections();
    }

    public Direction getDirection() {
        return direction;
    }

    public void turnRight() {
        this.direction = this.directions.get((this.direction.ordinal() + 1) % 4);
    }

    public void turnLeft() {
        this.direction = this.directions.get((this.direction.ordinal() + 3) % 4);
    }

    private boolean isTurnValid(Direction newDirection) {
        // if the direction value is offset by 2, turn cannot be made directly
        if ((this.direction.ordinal() - newDirection.ordinal()) % 2 == 0) {
            return false;
        }
        return true;
    }

    public void turnDrone(String dir) {
        Direction newDirection = Direction.valueOf(dir);
        // if the turn can be made directly, simply update the direction
        if (isTurnValid(newDirection)) {
            this.direction = newDirection;
        }
        // otherwise, make two turns before proceeding; prevents the drone from flipping
        else {
            turnRight();
            turnRight();
        }
    } 
}