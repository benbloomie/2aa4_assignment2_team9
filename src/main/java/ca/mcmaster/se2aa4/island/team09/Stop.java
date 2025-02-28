package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class Stop implements PropellerAction {

    @Override
    public JSONObject performAction() {
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");   
        return decision; 
    }
}

