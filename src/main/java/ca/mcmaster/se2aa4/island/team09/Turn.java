package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class Turn implements PropellerAction {
    private Direction turnDirection;
    private GPS gps;

    public Turn(Direction turnDirection, GPS gps) {
        this.turnDirection = turnDirection;
        this.gps = gps;
    }

    @Override
    public JSONObject performAction() {
        gps.setDirection(turnDirection);
        // creates a JSONObject to store the parameter information
        JSONObject parameters = new JSONObject();
        parameters.put("direction", gps.getDirection());

        // creates a JSONObject to register the action and its corresponding parameters
        JSONObject decision = new JSONObject();
        decision.put("action", "heading");
        decision.put("parameters", parameters);

        return decision;
    }


}
