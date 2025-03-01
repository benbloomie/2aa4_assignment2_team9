package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class Turn implements PropellerAction {
    private final CommandCenter commands;
    private Direction turnDirection;
    private GPS gps;

    public Turn(GPS gps, CommandCenter commands, String turnDirection) {
        this.gps = gps;
        this.commands = commands;
        this.turnDirection = Direction.valueOf(turnDirection);
    }

    @Override
    public void performAction() {
        if (!canDroneMakeTurn()) {
            makeUTurn();
            return;
        }
        gps.setDirection(turnDirection);
        // creates a JSONObject to store the parameter information
        JSONObject parameters = new JSONObject();
        parameters.put("direction", gps.getDirection());

        // creates a JSONObject to register the action and its corresponding parameters
        JSONObject decision = new JSONObject();
        decision.put("action", "heading");
        decision.put("parameters", parameters);

        commands.addCommand(decision);  // adds action to commands queue
    }


    public void turnRight() {
        turnDirection = gps.getRightDirection();  // updates turning direction to make proper turn
        performAction();
    }

    public void turnLeft() {
        turnDirection = gps.getLeftDirection();
        performAction();
    }

    private boolean canDroneMakeTurn() {
        // if the direction value does not differ by 2, the turn can be made directly
        return Math.abs(gps.getDirectionOrdinal() - turnDirection.ordinal()) != 2;
    }

    private void makeUTurn() {
        // initial placeholder: will need to implement logic to determine whether to make left or right U-Turn using the radar
            // GROUP MEMBERS NEED TO CODE RADAR CLASS
        turnRight();
        turnRight();
    }
}