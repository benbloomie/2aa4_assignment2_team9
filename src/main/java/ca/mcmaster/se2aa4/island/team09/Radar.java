package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class Radar {

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

    private Direction checkSurrondings() {
        // should check boundaires for other directions to determine which way the drone should turn
        return Direction.N;  // placeholder
    }
}