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

    // FLIES UNTIL BATTERY DIES --> SOMETIMES GOES OUT OF BOUNDS????
        // Don't think this will be a worry once the rest of the logic is done since the drone flies back
    @Override
    public String takeDecision() {
        ActionType prevAction = resultManager.getPreviousAction();

        if (drone.isInAction()) {
            JSONObject decision = drone.getDecision();
            logger.info("Drone in action: {}", decision.toString());
            return decision.toString();
        }

        else if (prevAction == ActionType.MOVEMENT) {
            drone.frontEcho();
        }

        else {
            if (resultManager.uTurnRequired()) {  // if a U-Turn is needed, perform it
                drone.turnDrone(gps.getOppositeDirection().toString());
            }
            else {  // otherwise, continue straight
                drone.moveForward();
            }
        }
        JSONObject decision = drone.getDecision();
        logger.info("Executing action: {}", decision.toString());
        return decision.toString();
    } 

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
