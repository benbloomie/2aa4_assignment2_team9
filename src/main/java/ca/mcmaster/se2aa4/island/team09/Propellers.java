package ca.mcmaster.se2aa4.island.team09;
import org.json.JSONObject;
import java.util.Queue;
import java.util.LinkedList;

public class Propellers {
    private GPS gps;
    private Queue<JSONObject> decisionQueue;
    private PropellerAction action;

    public Propellers(GPS gps) {
        this.gps = gps;
        this.decisionQueue = new LinkedList<>();
    }

    public void turnRight() {
        Direction newDirection = gps.getRightDirection();
        registerTurn(newDirection);
    }

    // not sure if i will need this, but leaving in for now
    public void turnLeft() {
        Direction newDirection = gps.getLeftDirection();
        registerTurn(newDirection);
    }

    private boolean canDroneMakeTurn(Direction newDirection) {
        // if the direction value does not differ by 2, the turn can be made directly
        return Math.abs(gps.getDirectionOrdinal() - newDirection.ordinal()) != 2;
    }

    public void turnDrone(String dir) {
        Direction newDirection = Direction.valueOf(dir);
        // if the turn can be made directly, update the direction; // otherwise, make a U-Turn
        if (canDroneMakeTurn(newDirection)) {
            registerTurn(newDirection);
        } else {
            makeUTurn();
        }
    } 

    public void moveForward() {
        action = new Fly();
        decisionQueue.add(action.performAction());
    }

    public void stopDrone() {
        action = new Stop();
        decisionQueue.add(action.performAction());
    }

    public JSONObject getMovement() {
        return decisionQueue.poll();  // retrieves and retruns decision to be made
    }
    
    public boolean inUTurn() {
        return (!decisionQueue.isEmpty()); // checks if the drone is in the middle of a U-Turn
    }

    private void makeUTurn() {
        // initial placeholder: will need to implement logic to determine whether to make left or right U-Turn using the radar
            // GROUP MEMBERS NEED TO CODE RADAR CLASS
        turnRight();
        turnRight();
    }

    private void registerTurn(Direction newDirection) {
        gps.setDirection(newDirection);  // sets new direction for the drone 

        // creates a JSONObject to store the parameter information
        JSONObject parameters = new JSONObject();
        parameters.put("direction", gps.getDirection());

        // creates a JSONObject to register the action and its corresponding parameters
        JSONObject decision = new JSONObject();
        decision.put("action", "heading");
        decision.put("parameters", parameters);
        decisionQueue.add(decision);
    }
}