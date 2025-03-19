package ca.mcmaster.se2aa4.island.team09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class ResponseCenter {
    private final Logger logger = LogManager.getLogger();
    private DroneState drone;
    private Island island;
    private RadarStatus radarStatus;
    private ScanManager scanManager;
    private ActionType actionProcessed;

    public ResponseCenter(DroneState drone, Island island) {
        this.drone = drone;
        this.island = island;
        this.radarStatus = new RadarStatus();
    }

    public void acknowledge(JSONObject response) {
        logger.info("** Response received:\n" + response.toString(2));
        processCost(response);
        processStatus(response);
        processExtras(response);
    }

    private void processCost(JSONObject response) {
        // processes cost information --> updates the drones battery correspondingly
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        drone.consumeBattery(cost);
    }

    private void processStatus(JSONObject response) {
        // analyzes the status of the drone
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
    }

    private void processExtras(JSONObject response) {
        // inteprets any extra information that the action produced (Echo & Scan)
        JSONObject extraInfo = response.getJSONObject("extras");
        if (!extraInfo.isEmpty()) {  // if extra information is present, process it
            analyzeExtras(extraInfo);
        }
        else {
            actionProcessed = ActionType.MOVEMENT;
        }
    }

    private void analyzeExtras(JSONObject extraInfo) {
        logger.info("Additional information received: {}", extraInfo);
        if (extraInfo.has("range")) {  // action was an ECHO
            actionProcessed = ActionType.ECHO;
            radarStatus.updateStatus(extraInfo);
            handleRadarStatus();
        }
        else {  // action was a SCAN
            actionProcessed = ActionType.SCAN;
        }
    }

    private void handleRadarStatus() {
        logger.info("Radar Status: [{},{}]", radarStatus.getRange(), radarStatus.getEcho());

        // initialize island information if it hasnt been set yet
        if (island.getX() == -1) {
            island.setX(radarStatus.getRange());
        }
        else if (island.getY() == -1) {
            island.setY(radarStatus.getRange());
        }
    }
    
    public ActionType getPreviousAction() {
        return actionProcessed;
    }

    public boolean uTurnRequired() {
        EchoStatus recentEcho = radarStatus.getEcho();
        // if our next fly reads OUT_OF_RANGE (off map) indicate a U-Turn needs to be performed
        if (recentEcho == EchoStatus.OUT_OF_RANGE) {
            return true;
        }
        return false;
    }

    public RadarStatus getRadarStatus() {
        return radarStatus;
    }
}