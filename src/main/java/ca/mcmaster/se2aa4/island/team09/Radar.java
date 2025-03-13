package ca.mcmaster.se2aa4.island.team09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
public class Radar {

    private final Logger logger = LogManager.getLogger();

    public Radar() {
    }

    public boolean isBoundaryAhead(Direction direction) {
        // need to add logic
        return false;  // placeholder
    }

    private void getEcho(Direction echoDirection, CommandCenter commandCenter) { // find the echo value and send it to the command center
        JSONObject decision = new JSONObject();
        decision.put("action", "echo");
        decision.put("parameters", echoDirection);
        commandCenter.addCommand(decision);
    }

    public void noseEcho(GPS gps, CommandCenter commandCenter){ // find the echo value from the nose, and send it to the command center
        Direction noseDirection = gps.getDirection();
        getEcho(noseDirection, commandCenter);
    }

    public void leftEcho(GPS gps, CommandCenter commandCenter){ // find the echo value from the left radar and send it to the command center
        Direction leftWingDirection = gps.getLeftDirection();
        getEcho(leftWingDirection, commandCenter);
    }

    public void rightEcho(GPS gps, CommandCenter commandCenter){ // find the echo value from the right radar and send it to the command center
        Direction rightWingDirection = gps.getRightDirection();
        getEcho(rightWingDirection, commandCenter);
    }
}