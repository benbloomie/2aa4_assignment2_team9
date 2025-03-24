package ca.mcmaster.se2aa4.island.team09;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import eu.ace_design.island.bot.IExplorerRaid;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private CommandCenter commandCenter;
    private Drone drone;
    private ResponseCenter resultManager;
    private Island island;
    private Queue<SearchPhase> searchPhases;
    // ADDED VARIABLES
    private int xStart = 1;
    private int yStart = 1;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}", info.toString(2));

        String startingDirection = info.getString("heading").toUpperCase();
        Integer batteryCapacity = info.getInt("budget");

        this.drone = new Drone(startingDirection, new Battery(batteryCapacity), new Coordinate(xStart, yStart));
        this.commandCenter = new CommandCenter();
        this.island = new Island();

        this.resultManager = new ResponseCenter(drone, island, drone.getGPS());

        // ADDED INITIALIZATIONS

        logger.info("The drone is facing {}", drone.getDirection());
        logger.info("Battery level is {}", drone.getBatteryLevel());

        // Initialize search phases
        this.searchPhases = new LinkedList<>();
        searchPhases.add(new IslandGenerator(drone, commandCenter, island));
        searchPhases.add(new IslandLocater(drone, commandCenter, island));
        searchPhases.add(new IslandPatrol(drone, commandCenter, island));
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

    @Override
    public void acknowledgeResults(String s) {
        JSONObject scannerResponse = new JSONObject(new JSONTokener(new StringReader(s)));
        resultManager.acknowledge(scannerResponse);

    }

    // Tentative
    @Override
    public String deliverFinalReport() {
        logger.info("Delivering final report");
        logger.info("Emergency Site ID: " + resultManager.getEmergencySite());
        logger.info("Creek IDs Found: " + resultManager.getCreekIds());
        return "Emergency Site ID: " + resultManager.getEmergencySite() + "\nCreek IDs Found: "
                + resultManager.getCreekIds();
    }
}
