package ca.mcmaster.se2aa4.island.team09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
public class Radar {
    private final Logger logger = LogManager.getLogger();
    private GPS gps;

    public Radar(GPS gps) {
        this.gps = gps;
    }

    public boolean isBoundaryAhead(Direction direction) {
        // need to add logic
        return false;  // placeholder
    }

    private void getEcho(Direction echoDirection, CommandCenter commandCenter) { // find the echo value and send it to the command center
        // creates a JSONObject to store the parameter information
        JSONObject parameters = new JSONObject();
        parameters.put("direction", echoDirection);

        // creates a JSONObject to register the action and its corresponding parameters
        JSONObject decision = new JSONObject();
        decision.put("action", "echo");
        decision.put("parameters", parameters);
        commandCenter.addCommand(decision);  // adds action to commands queue
    }

    public void noseEcho(CommandCenter commandCenter){ // find the echo value from the nose, and send it to the command center
        Direction noseDirection = gps.getDirection();
        getEcho(noseDirection, commandCenter);
    }

    public void leftEcho(CommandCenter commandCenter){ // find the echo value from the left radar and send it to the command center
        Direction leftWingDirection = gps.getLeftDirection();
        getEcho(leftWingDirection, commandCenter);
    }

    public void rightEcho(CommandCenter commandCenter){ // find the echo value from the right radar and send it to the command center
        Direction rightWingDirection = gps.getRightDirection();
        getEcho(rightWingDirection, commandCenter);
    }
}