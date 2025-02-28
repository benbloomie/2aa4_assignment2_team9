package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class Fly implements PropellerAction {

    @Override
    public JSONObject performAction() {
        JSONObject decision = new JSONObject();
        decision.put("action", "fly"); 
        return decision;
    }
}
