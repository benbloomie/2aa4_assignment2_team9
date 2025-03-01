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

    public JSONObject getEcho(Direction direction) {
        JSONObject decision = new JSONObject();
        decision.put("action", "echo");
        decision.put("parameters", new JSONObject().put("direction", direction.toString()));
        
        return decision;
    }

    public void check(){
        logger.info("DIRECTION CHECK");
    }

    //use getEcho class to find forward

    //use getEcho class to find right

    //use getEcho class to find left

    //need a way to check the echo status (must incorporate echo status class)



    private Direction checkSurrondingsNorth() {
        // should check boundaires for other directions to determine which way the drone should turn
        return Direction.N;  // placeholder
    }

    private Direction checkSurrondingsEast() {
        // should check boundaires for other directions to determine which way the drone should turn
        return Direction.E;  // placeholder
    }

    private Direction checkSurrondingsWest() {
        // should check boundaires for other directions to determine which way the drone should turn
        return Direction.W;  // placeholder
    }




}