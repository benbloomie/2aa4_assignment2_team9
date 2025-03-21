package ca.mcmaster.se2aa4.island.team09;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.LinkedList;
import java.util.Queue;

import eu.ace_design.island.bot.IExplorerRaid;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private CommandCenter commandCenter;
    private CreekStorage creekStorage;
    private DroneState drone;
    private ResponseCenter resultManager;
    private Island island;
    private Queue<SearchPhase> searchPhases;
    // ADDED VARIABLES
    private PhotoScanner photoScanner;
    private ScanManager scanManager;
    private String emergencySiteId = null;

    private int xStart = 1;
    private int yStart = 1;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}", info.toString(2));

        String startingDirection = info.getString("heading").toUpperCase();
        Integer batteryCapacity = info.getInt("budget");

        this.drone = new DroneState(startingDirection, new Battery(batteryCapacity), new Coordinate(xStart, yStart));
        this.commandCenter = new CommandCenter();
        this.creekStorage = new CreekStorage();
        this.island = new Island();
        this.resultManager = new ResponseCenter(drone, island);

        // ADDED INITIALIZATIONS
        this.photoScanner = new PhotoScanner();
        this.scanManager = new ScanManager();

        logger.info("The drone is facing {}", drone.getDirection());
        logger.info("Battery level is {}", drone.getBatteryLevel());

        // TESTING
        this.searchPhases = new LinkedList<>();
        searchPhases.add(new IslandGenerator(drone, commandCenter, resultManager, island));
        searchPhases.add(new IslandLocater(drone, commandCenter, resultManager, island));
    }

    @Override
    public String takeDecision() {
        if (commandCenter.isDroneInAction()) {
            JSONObject decision = commandCenter.getNextCommand();
            logger.info("Drone in action: {}", decision.toString());
            return decision.toString();
        }

        if (searchPhases.peek().isActionComplete()) {
            logger.info("Phase {} has been completed!", searchPhases.peek());
            searchPhases.remove();
        }

        searchPhases.peek().executeStep();
        JSONObject decision = commandCenter.getNextCommand();
        logger.info("Executing action: {}", decision.toString());
        return decision.toString();
    }

    /*   FLIES UNTIL BATTERY DIES --> SOMETIMES GOES OUT OF BOUNDS????
        //should be able to use this for IslandPatrol phase
    @Override
    public String takeDecision() {
        ActionType prevAction = resultManager.getPreviousAction();

        if (commandCenter.isDroneInAction()) {
            JSONObject decision = commandCenter.getNextCommand();
            logger.info("Drone in action: {}", decision.toString());
            return decision.toString();
        }

        else if (prevAction == ActionType.MOVEMENT) {
            drone.frontEcho(commandCenter);
        }

        else {
            if (resultManager.uTurnRequired()) {  // if a U-Turn is needed, perform it
                drone.turnDrone(gps.getOppositeDirection(), commandCenter);
            }
            else {  // otherwise, continue straight
                drone.moveForward(commandCenter);
            }
        }
        JSONObject decision = commandCenter.getNextCommand();
        logger.info("Executing action: {}", decision.toString());
        return decision.toString();
    } */

    @Override
    public void acknowledgeResults(String s) {
        JSONObject scannerResponse = new JSONObject(new JSONTokener(new StringReader(s)));
        resultManager.acknowledge(scannerResponse);

        // ADDED SCANNING LOGIC
        ScanResult result = photoScanner.scan(scannerResponse.toString());
        scanManager.addScan(result);
        creekStorage.addCreeks(result.getCreeks());

        if (result.hasSite()) {
            emergencySiteId = result.getSite();
        }
    }

    // Tentative
    @Override
    public String deliverFinalReport() {
        return "Emergency Site ID: " + emergencySiteId + "\nCreek IDs Found: " + creekStorage.getAllCreekIds();
    }
}
