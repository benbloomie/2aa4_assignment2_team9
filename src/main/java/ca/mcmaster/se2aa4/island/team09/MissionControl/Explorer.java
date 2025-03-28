package ca.mcmaster.se2aa4.island.team09.MissionControl;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.mcmaster.se2aa4.island.team09.Drone.Coordinate;
import ca.mcmaster.se2aa4.island.team09.Drone.Drone;
import ca.mcmaster.se2aa4.island.team09.Drone.Battery;
import ca.mcmaster.se2aa4.island.team09.Environment.Map;
import ca.mcmaster.se2aa4.island.team09.Environment.MapGenerator;
import eu.ace_design.island.bot.IExplorerRaid;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private CommandCenter commandCenter;
    private Drone drone;
    private ResponseCenter resultManager;
    private Queue<SearchPhase> searchPhases;
    // ADDED VARIABLES
    private int xStart = 1;
    private int yStart = 1;

    @Override
    public void initialize(String s) {
        logger.trace("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.trace("** Initialization info:\n {}", info.toString(2));

        String startingDirection = info.getString("heading").toUpperCase();
        Integer batteryCapacity = info.getInt("budget");

        this.drone = new Drone(startingDirection, new Battery(batteryCapacity), new Coordinate(xStart, yStart));
        this.commandCenter = new CommandCenter();
        Map map = new Map();

        this.resultManager = new ResponseCenter(drone, map, drone.getGPS());

        // ADDED INITIALIZATIONS

        logger.trace("The drone is facing {}", drone.getDirection());
        logger.trace("Battery level is {}", drone.getBatteryLevel());

        // Initialize search phases
        this.searchPhases = new LinkedList<>();
        searchPhases.add(new MapGenerator(drone, commandCenter, map));
        searchPhases.add(new IslandLocater(drone, commandCenter, map));
        searchPhases.add(new IslandPatrol(drone, commandCenter, map));
    }

    @Override
    public String takeDecision() {
        if (commandCenter.isDroneInAction()) {
            JSONObject decision = commandCenter.getNextCommand();
            logger.trace("Drone in action: {}", decision.toString());
            return decision.toString();
        }

        if (searchPhases.peek().isActionComplete()) {
            logger.trace("Phase {} has been completed!", searchPhases.peek());
            searchPhases.remove();
        }

        searchPhases.peek().executeStep();
        JSONObject decision = commandCenter.getNextCommand();
        logger.trace("Executing action: {}", decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject scannerResponse = new JSONObject(new JSONTokener(new StringReader(s)));
        resultManager.acknowledge(scannerResponse);

    }

    @Override
    public String deliverFinalReport() {
        logger.info("Delivering final report");
        logger.info("Emergency Site ID: " + resultManager.getEmergencySite().getId());
        logger.info("Creek IDs Found: " + resultManager.getCreekIds());
        logger.info("The closest creek: " + resultManager.getNearestCreek());
        return "Emergency Site ID: " + resultManager.getEmergencySite() + "\nCreek IDs Found: " + resultManager.getCreekIds();
    }
}
