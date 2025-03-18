package ca.mcmaster.se2aa4.island.team09;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import eu.ace_design.island.bot.IExplorerRaid;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private CreekStorage creekStorage;
    private DroneState drone;
    private ResponseCenter resultManager;
    private GPS gps;  // explorer should also have access to the gps of the drone to determine how it should move 

    private int xStart = 0;
    private int yStart = 0;

    // TEMPORARY TESTING VARIABLES
    private int moveForward = 0;
    private int turnCount = 0;
    private int uTurnCount = 0;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        this.creekStorage = new CreekStorage();
        logger.info("** Initialization info:\n {}",info.toString(2));

        String startingDirection = info.getString("heading").toUpperCase();
        Integer batteryCapacity = info.getInt("budget");
        
        this.drone = new DroneState(startingDirection, new Battery(batteryCapacity), new Coordinate(xStart, yStart));
        this.resultManager = new ResponseCenter(drone);
        this.gps = drone.getGPS();

        logger.info("The drone is facing {}", drone.getDirection());
        logger.info("Battery level is {}", drone.getBatteryLevel());
    }

    // TEMPORARY TESTING
    @Override
    public String takeDecision() {
        JSONObject decision;
        // if drone is moving, complete right turn (KEEP THIS)
        if (drone.isInAction()) {
        }
        // testing moving forward; flies 10 times
        else if (moveForward < 10) {
            drone.moveForward();
            moveForward++;
        }
        // testing turning; turns 5 times
        else if (turnCount <= 4){
            drone.turnDrone(gps.getRightDirection().toString());
            turnCount++;
        }
        // testing u-turn
        else if (uTurnCount < 1) {
            drone.turnDrone(gps.getOppositeDirection().toString());
            uTurnCount++;
        }
        // stop after testing all movements
        else {
            drone.stopDrone();
        }
        decision = drone.getDecision();
        return decision.toString();
    } 

    /*
    @Override
    public String takeDecision() {
        ActionType prevAction = resultManager.getPreviousAction();

        if (drone.isInAction()) {
            JSONObject decision = drone.getDecision();
            return decision.toString();
        }

        if (prevAction == ActionType.MOVEMENT) {
            drone.frontEcho();
        }

        // testing moving forward; flies 10 times
        else if (moveForward < 10) {
            drone.moveForward();
            moveForward++;
        }
        // testing turning; turns 5 times
        else if (turnCount <= 4){
            drone.turnDrone(gps.getRightDirection().toString());
            turnCount++;
        }
        // testing u-turn
        else if (uTurnCount < 1) {
            drone.turnDrone(gps.getOppositeDirection().toString());
            uTurnCount++;
        }
        // stop after testing all movements
        else {
            
            drone.stopDrone();
        }
        JSONObject decision = drone.getDecision();
        return decision.toString();
    } */

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        resultManager.acknowledge(response);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }
}
