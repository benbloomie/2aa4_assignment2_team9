package ca.mcmaster.se2aa4.island.team09;

public class GPS {
    private Direction direction;
    
    public GPS(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getDirectionOrdinal() {
        return direction.ordinal();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void updateDirection(int turnValue) {
        // calculates the ordinal of the new direction using modular arithmetic
        int newDirection = (direction.ordinal() + turnValue) % 4;  
        direction = Direction.values()[newDirection];  // updates new direction using its ordinal

    }
}