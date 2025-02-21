package ca.mcmaster.se2aa4.island.team09;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private DroneState drone;
    private int moveForward = 0;
    private int turnCount = 0;
    private int uTurnCount = 0;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String startingDirection = info.getString("heading").toUpperCase();
        Integer batteryCapacity = info.getInt("budget");
        drone = new DroneState(startingDirection, new Battery(batteryCapacity));
        logger.info("The drone is facing {}", drone.getDirection());
        logger.info("Battery level is {}", drone.getBatteryLevel());
    }

    // TEMPORARY TESTING
    @Override
    public String takeDecision() {
        JSONObject decision;
        // if drone is moving, complete right turn (KEEP THIS)
        if (drone.isDroneMoving()) {
            return drone.getDecision().toString();
        }
        // testing moving forward
        if (moveForward < 10) {
            drone.moveForward();
            decision = drone.getDecision();
            moveForward++;
        }
        // testing turning
        else if (turnCount <= 4){
            drone.turnDrone((drone.getDirection().getDirections().get((drone.getDirection().ordinal() + 1) % 4)).toString());
            decision = drone.getDecision();
            turnCount++;
        }
        // testing u-turn
        else if(uTurnCount < 2) {
            drone.turnDrone((drone.getDirection().getDirections().get((drone.getDirection().ordinal() + 2) % 4)).toString());
            decision = drone.getDecision();
            uTurnCount++;
        }
        // move forward until out of bounds
        else {
            drone.moveForward(); 
            decision = drone.getDecision();
        }
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        drone.useBattery(cost);
        logger.info("The battery of the drone is {}", drone.getBatteryLevel());
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }
}
