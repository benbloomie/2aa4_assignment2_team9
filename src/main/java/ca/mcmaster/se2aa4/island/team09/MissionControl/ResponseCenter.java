package ca.mcmaster.se2aa4.island.team09.MissionControl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team09.Drone.Drone;
import ca.mcmaster.se2aa4.island.team09.Drone.GPS;
import ca.mcmaster.se2aa4.island.team09.Environment.Creek;
import ca.mcmaster.se2aa4.island.team09.Environment.EmergencySite;
import ca.mcmaster.se2aa4.island.team09.Environment.Map;
import ca.mcmaster.se2aa4.island.team09.Environment.PoIStorage;

import org.json.JSONArray;

public class ResponseCenter {
    private final Logger logger = LogManager.getLogger();
    private final GPS gps;
    private Drone drone;
    private Map map;
    private PoIStorage locationStorage;
    private RadarStatus radarStatus;

    public ResponseCenter(Drone drone, Map map, GPS gps) {
        this.drone = drone;
        this.map = map;
        this.radarStatus = new RadarStatus();
        this.locationStorage = new PoIStorage();
        this.gps = gps;
    }

    public void acknowledge(JSONObject response) {
        logger.trace("** Response received:\n" + response.toString(2));
        processCost(response);
        processStatus(response);
        processExtras(response);
    }

    private void processCost(JSONObject response) {
        // processes cost information --> updates the drones battery correspondingly
        Integer cost = response.getInt("cost");
        logger.trace("The cost of the action was {}", cost);
        drone.consumeBattery(cost);
    }

    private void processStatus(JSONObject response) {
        // analyzes the status of the drone
        String status = response.getString("status");
        logger.trace("The status of the drone is {}", status);
    }

    private void processExtras(JSONObject response) {
        // inteprets any extra information that the action produced (Echo & Scan)
        JSONObject extraInfo = response.getJSONObject("extras");
        if (!extraInfo.isEmpty()) { // if extra information is present, process it
            analyzeExtras(extraInfo);
        }
    }

    private void analyzeExtras(JSONObject extraInfo) {
        logger.trace("Additional information received: {}", extraInfo);
        if (extraInfo.has("range")) { // action was an ECHO
            radarStatus.updateStatus(extraInfo);
            handleRadarStatus();
        } else { // action was a SCAN
            handleScanResult(extraInfo);
        }
    }

    private void handleRadarStatus() {
        logger.trace("Radar Status: [{},{}]", radarStatus.getRange(), radarStatus.getEcho());

        // initialize island information if it hasnt been set yet
        if (map.getX() == -1) {
            map.setX(radarStatus.getRange());
        } else if (map.getY() == -1) {
            map.setY(radarStatus.getRange());
        }
    }

    public void handleScanResult(JSONObject extras) {
        JSONArray creeksInfo = extras.getJSONArray("creeks"); // retrieves the associated id with the creek
        int xPos = gps.getXCord();
        int yPos = gps.getYCord();

        // processes each id recognized by the scan
        for (int i = 0; i < creeksInfo.length(); i++) {
            String creekId = creeksInfo.getString(0);
            locationStorage.addCreek(new Creek(creekId, xPos, yPos));
        }

        JSONArray siteInfo = extras.getJSONArray("sites"); // retrieves the associated id with the emergecny site
        if (siteInfo.length() > 0) { // if true, scan found a site --> update emergecny site id
            String emergencySiteId = siteInfo.getString(0);
            locationStorage.setEmergencySite(new EmergencySite(emergencySiteId, xPos, yPos));
        }
    }

    public RadarStatus getRadarStatus() {
        return radarStatus;
    }

    public String getCreekIds() {
        return locationStorage.getCreekResults();
    }

    public EmergencySite getEmergencySite() {
        return locationStorage.getEmergencySite();
    }

    public String getNearestCreek() {
        EmergencySite emergencySite = locationStorage.getEmergencySite();
        Creek nearestCreek = locationStorage.findNearestCreek(emergencySite.getX(), emergencySite.getY());
        if (nearestCreek != null) {
            return nearestCreek.getId();
        } else {
            return "No creeks found";
        }
    }
}