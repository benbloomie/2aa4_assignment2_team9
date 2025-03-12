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

    public void getEcho(Direction echoDirection, GPS gps, CommandCenter commandCenter) {
        JSONObject decision = new JSONObject();
        decision.put("action", "echo");
        decision.put("parameters", echoDirection);
        commandCenter.addCommand(decision);
    }

    private void setDirections(GPS gps){
        Direction noseDirection = gps.getDirection();
        Direction leftWingDirection = gps.getLeftDirection();
        Direction rightWingDirection = gps.getRightDirection();
    }

}