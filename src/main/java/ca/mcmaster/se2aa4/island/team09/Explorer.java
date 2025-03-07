package ca.mcmaster.se2aa4.island.team09;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.Map;

import eu.ace_design.island.bot.IExplorerRaid;
import scala.math.Fractional;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private CreekStorage creekStorage;
    private DroneState drone;
    private Radar radar;
    private RadarManager radarManager;
    private GPS gps;  // explorer should also have access to the gps of the drone to determine how it should move 
    // TEMPORARY TESTING VARIABLES
    private int moveForward = 0;
    private int turnCount = 0;
    private int uTurnCount = 0;
    private int numOfEchos;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        this.creekStorage = new CreekStorage();
        logger.info("** Initialization info:\n {}",info.toString(2));

        String startingDirection = info.getString("heading").toUpperCase();
        Integer batteryCapacity = info.getInt("budget");
        

        this.drone = new DroneState(startingDirection, new Battery(batteryCapacity));
        this.gps = drone.getGPS();

    

        logger.info("The drone is facing {}", drone.getDirection());
        logger.info("Battery level is {}", drone.getBatteryLevel());
    }

    // TEMPORARY TESTING
    @Override
    public String takeDecision() {
        JSONObject decision;

    
        // if drone is moving, complete right turn (KEEP THIS)
        if (drone.isDroneMoving()) {
        }
        // testing moving forward; flies 10 times
        if (moveForward < 10) {
            drone.moveForward();
            moveForward++;
        }
        // testing turning; turns 5 times
        else if (turnCount <= 4){
            drone.turnDrone(gps.getRightDirection().toString());
            turnCount++;
        }
        // testing u-turn
        else if(uTurnCount < 1) {
            drone.turnDrone("N");
            uTurnCount++;
        }
        // stop after testing all movements
        else {
            drone.stopDrone();
        }
        decision = drone.getDecision();
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);

        // stuff i have added
        drone.consumeBattery(cost);
        logger.info("The battery of the drone is {}", drone.getBatteryLevel());
        // something to check radar status??

        if (extraInfo.length() == 2){ // when extraInfo has 2 elements, it from the radar method 
            radarManager.setJSON(extraInfo);
            logger.info("Radar Status: " + radarManager.getStatus(numOfEchos));
            numOfEchos++;
        }
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }
}
