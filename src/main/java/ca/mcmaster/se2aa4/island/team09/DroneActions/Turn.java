package ca.mcmaster.se2aa4.island.team09.DroneActions;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team09.Drone.Direction;
import ca.mcmaster.se2aa4.island.team09.Drone.GPS;
import ca.mcmaster.se2aa4.island.team09.MissionControl.CommandCenter;

public class Turn implements Action {
    private final CommandCenter commands;
    private Direction turnDirection;
    private GPS gps;

    public Turn(GPS gps, CommandCenter commands, Direction turnDirection) {
        this.gps = gps;
        this.commands = commands;
        this.turnDirection = turnDirection;
    }

    @Override
    public void performAction() {
        if (!canDroneMakeTurn()) {  // checks if the drone can directly make the turn before processing 
            makeUTurn();
            return;
        }
        gps.updateCoordinates();  // updates coordinates for first part of the turn (original direction)
        gps.setDirection(turnDirection);
        gps.updateCoordinates();  // updates coordinates using new direction

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
        // turns corresponding to the current direction it faces to continue the patrol
        if (gps.getDirection().ordinal() < 2) {  // handles N and E turns
            turnRight();
            turnRight();
        } 
        else {  // handles S and W turns
            turnLeft();
            turnLeft();
        }
    }
}