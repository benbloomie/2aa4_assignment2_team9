package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class Turn implements PropellerAction {
    private Direction turnDirection;

    @Override
    public JSONObject performAction() {

        return new JSONObject();
    }

    public void setTurnDirection(Direction turnDirection) {
        this.turnDirection = turnDirection;
    }
}
