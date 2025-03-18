package ca.mcmaster.se2aa4.island.team09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class ResponseCenter {
    private final Logger logger = LogManager.getLogger();
    private DroneState drone;
    private RadarManager radarManager;
    private int numOfEchos;
    private ActionType actionProcessed;

    public ResponseCenter(DroneState drone) {
        this.drone = drone;
        this.radarManager = new RadarManager();
        this.numOfEchos = 0;
    }

    public void acknowledge(JSONObject response) {
        logger.info("** Response received:\n" + response.toString(2));

        // processes cost information --> updates the drones battery correspondingly
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        drone.consumeBattery(cost);
        logger.info("The battery of the drone is {}", drone.getBatteryLevel());

        // analyzes the status of the drone
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);

        // inteprets any extra information that the action produced (Echo & Scan)
        JSONObject extraInfo = response.getJSONObject("extras");
        if (!extraInfo.isEmpty()) {  // if extra information is present, process it
            analyzeExtras(extraInfo);
        }
        else {
            actionProcessed = ActionType.MOVEMENT;
        }
    }

    public void analyzeExtras(JSONObject extraInfo) {
        logger.info("Additional information received: {}", extraInfo);
        if (extraInfo.has("range")) {  // action was an ECHO
            actionProcessed = ActionType.ECHO;
            radarManager.updateStatus(extraInfo);
            logger.info("Radar Status: " + radarManager.getStatus(numOfEchos));
            numOfEchos++;
        }
        else {  // action was a SCAN

        }
    }
    
    public ActionType getPreviousAction() {
        return actionProcessed;
    }
}
