package ca.mcmaster.se2aa4.island.team09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Radar{
    private final Logger logger = LogManager.getLogger();
    private GPS gps;
    private Action action;

    public Radar(GPS gps) {
        this.gps = gps;
    }

    public boolean isBoundaryAhead(Direction direction) {
        // need to add logic
        return false;  // placeholder
    }

    public void noseEcho(CommandCenter commands){ // find the echo value from the nose, and send it to the command center
        Direction noseDirection = gps.getDirection();
        action = new Echo(noseDirection, commands);
        action.performAction();
    }

    public void leftEcho(CommandCenter commands){ // find the echo value from the nose, and send it to the command center
        Direction leftWingDirection = gps.getLeftDirection();
        action = new Echo(leftWingDirection, commands);
        action.performAction();
    }

    public void rightEcho(CommandCenter commands){ // find the echo value from the nose, and send it to the command center
        Direction rightWingDirection = gps.getRightDirection();
        action = new Echo(rightWingDirection, commands);
        action.performAction();
    }
}