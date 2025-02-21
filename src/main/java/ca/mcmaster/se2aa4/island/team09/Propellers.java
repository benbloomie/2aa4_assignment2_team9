package ca.mcmaster.se2aa4.island.team09;
import org.json.JSONObject;
import java.util.Queue;
import java.util.LinkedList;

public class Propellers {
    private GPS gps;
    private Queue<JSONObject> decisionQueue;

    public Propellers(GPS gps) {
        this.gps = gps;
        this.decisionQueue = new LinkedList<>();
    }

    public void turnRight() {
        gps.updateDirection(1);  // updates the direction to turn right using a value of 1
        JSONObject decision = new JSONObject();
        decision.put("action", "heading");
        decision.put("parameters", new JSONObject().put("direction", gps.getDirection()));
        decisionQueue.add(decision);
    }

    // not sure if i will need this, but leaving in for now
    public void turnLeft() {
        gps.updateDirection(3);  // updates the direction to turn left using a value of 3
        JSONObject decision = new JSONObject();
        decision.put("action", "heading");
        decision.put("parameters", new JSONObject().put("direction", gps.getDirection()));
        decisionQueue.add(decision);
    }

    private boolean canDroneMakeTurn(Direction newDirection) {
        // if the direction value does not differ by 2, the turn can be made directly
        return Math.abs(gps.getDirectionOrdinal() - newDirection.ordinal()) != 2;
    }

    public void turnDrone(String dir) {
        Direction newDirection = Direction.valueOf(dir);
        
        // if the turn can be made directly, simply update the direction
        if (canDroneMakeTurn(newDirection)) {
            gps.setDirection(newDirection);
            JSONObject decision = new JSONObject();
            decision.put("action", "heading");
            decision.put("parameters", new JSONObject().put("direction", dir));
            decisionQueue.add(decision);
        }
        // otherwise, make two turns before proceeding; prevents the drone from flipping
        else {
            turnRight();
            turnRight();
        }
    } 

    public void moveForward() {
        JSONObject decision = new JSONObject();
        decision.put("action", "fly"); 
        decisionQueue.add(decision);
    }

    public JSONObject getMovements() {
        return decisionQueue.poll();  // retrieves and retruns decision to be made
    }
    
    public boolean inUTurn() {
        return (!decisionQueue.isEmpty()); // checks if the drone is in the middle of a U-Turn
    }
}