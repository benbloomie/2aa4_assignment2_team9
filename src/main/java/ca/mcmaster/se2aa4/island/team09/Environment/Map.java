package ca.mcmaster.se2aa4.island.team09.Environment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Map {
    private final Logger logger = LogManager.getLogger();
    private int x;  // records the width of the map
    private int y;  // records the height of the map

    public Map() {
        // sets default values upon initialization to indicate the x and y have not been set
        this.x = -1;
        this.y = -1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        logger.trace("Set island x-value to: {}", x);
        this.x = x;
    }

    public void setY(int y) {
        logger.trace("Set island y-value to: {}", y);
        this.y = y;
    }
}