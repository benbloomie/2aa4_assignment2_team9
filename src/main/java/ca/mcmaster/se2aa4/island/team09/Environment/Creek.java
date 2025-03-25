package ca.mcmaster.se2aa4.island.team09.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Creek extends Location {

    private final Logger logger = LogManager.getLogger();

    public Creek(String Id, int x, int y) {
        super(Id, x, y);
    }
     
    public int getDistanceTo(int startingX, int startingY) {
        logger.trace("Creek x: " + this.x);
        logger.trace("Creek y: " + this.y);
        logger.trace("Starting x: " + startingX);
        logger.trace("Starting y: " + startingY);
        return Math.abs(this.x - startingX) + Math.abs(this.y - startingY);
    }
    
}