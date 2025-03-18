package ca.mcmaster.se2aa4.island.team09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class ResponseCenter {
    private final Logger logger = LogManager.getLogger();
    private DroneState drone;
    private RadarManager radarManager;
    private int numOfEchoes;
    private ActionType actionProcessed;

    public ResponseCenter(DroneState drone) {
        this.drone = drone;
        this.radarManager = new RadarManager();
        this.numOfEchoes = -1;
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
            numOfEchoes++;
            logger.info("Radar Status: [{},{}]", radarManager.getRangeStatus(numOfEchoes), radarManager.getEchoStatus(numOfEchoes));

        }
        else {  // action was a SCAN

        }
    }
    
    public ActionType getPreviousAction() {
        return actionProcessed;
    }

    public boolean uTurnRequired() {
        // if no actions have been processed yet, return false right away
        if (actionProcessed == null) {
            return false;
        }
        EchoStatus recentEcho = radarManager.getEchoStatus(numOfEchoes);
        int rangeToFound = radarManager.getRangeStatus(numOfEchoes);
        // if our next fly would put is out of bounds, indicate a U-Turn needs to be performed
        if (recentEcho == EchoStatus.OUT_OF_RANGE && rangeToFound == 2) {
            return true;
        }
        return false;
    }
}
