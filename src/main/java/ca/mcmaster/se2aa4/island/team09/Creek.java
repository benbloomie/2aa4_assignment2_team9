package ca.mcmaster.se2aa4.island.team09;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Creek extends Location {

    private final Logger logger = LogManager.getLogger();

    public Creek(String Id, int x, int y) {
        super(Id, x, y);
    }
     
    public int getDistanceTo(int startingX, int startingY) {
        logger.info("Creek x: " + this.x);
        logger.info("Creek y: " + this.y);
        logger.info("Starting x: " + startingX);
        logger.info("Starting y: " + startingY);
        return Math.abs(this.x - startingX) + Math.abs(this.y - startingY);
    }
    
}