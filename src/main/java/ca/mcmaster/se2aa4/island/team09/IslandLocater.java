package ca.mcmaster.se2aa4.island.team09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IslandLocater extends SearchPhase {
    private final Logger logger = LogManager.getLogger();
    private int movesMadeToMiddle;
    private int movesMadeToIsland;
    private int movesNeededToMiddle;
    private int movesNeededToIsland;
    private int rangeToGround;

    public IslandLocater(DroneState drone, CommandCenter commandCenter, ResponseCenter responseCenter, Island island) {
        super(drone, commandCenter, responseCenter, island);
        // calculates moves needed to island based on starting position
        this.movesMadeToMiddle = 0;
        this.movesMadeToIsland = 0;
    }

    @Override
    public void executeStep() {
        if (!movesAreSet()) {
            setMovesNeeded();
        }

        // moves drone forward to middle position
        if (movesMadeToMiddle < movesNeededToMiddle) {
            moveToMiddle();
            return;
        }
        // turns drone when it reaches the middle of the map
        else if (movesMadeToMiddle == movesNeededToMiddle) {
            faceIsland();
            return;
        }

        // moves drone forward until it reaches GROUND 
        if (movesMadeToIsland < movesNeededToIsland) {
            moveToIsland();
            return;
        }
        // phase is completed
        drone.turnDrone(Direction.E, commandCenter);
        phaseCompleted = true;
    }

    private void moveToMiddle() {
        drone.moveForward(commandCenter);
        movesMadeToMiddle++;
    }

    private void faceIsland() {
        // updates the drone to face the island; scans to get distance to island
        drone.turnDrone(drone.getGPS().getRightDirection(), commandCenter);
        drone.echo(drone.getGPS().getDirection(), commandCenter);
        movesMadeToMiddle++;
    }

    private void moveToIsland() {
        drone.moveForward(commandCenter);
        movesMadeToIsland++;
    }

    private boolean movesAreSet() {
        return movesNeededToIsland != 0;
    }

    private void setMovesNeeded() {
        // sets the amount of moves the drone needs to make to get to the islands middle position
        this.movesNeededToMiddle = Math.abs(drone.getGPS().getXCord() - (island.getX() / 2)) - 2;
        this.movesNeededToIsland = Math.abs(drone.getGPS().getYCord() - (island.getY() / 2)) - 2;
        logger.info("Moves needed to middle: " + movesNeededToMiddle);
        logger.info("Moves needed to island: " + movesNeededToIsland);
    }
}