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

    private void noseEcho(){
        JSONObject parameters = new JSONObject();
        parameters.put("direction", noseDirection);

        // creates a JSONObject to register the action and its corresponding parameters
        JSONObject decision = new JSONObject();
        decision.put("action", "echo");
        decision.put("parameters", parameters);
    }

    public JSONObject check(){
        logger.info("DIRECTION CHECK");
        Direction north = Direction.N;
        //Direction east = Direction.E;
        return getEcho(north);
    }

    

    private JSONObject scanResult(){
        JSONObject decision = new JSONObject();
        decision.put("result", "echo");
        return decision;
    }

}