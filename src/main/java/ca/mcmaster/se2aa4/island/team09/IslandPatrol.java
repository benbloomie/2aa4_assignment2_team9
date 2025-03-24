package ca.mcmaster.se2aa4.island.team09;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IslandPatrol extends SearchPhase {
    private int stepCounter;
    private int moveCounter; // progress towards completion of a segment of the algorithm
    private int segmentLength = 1;
    private boolean shouldScan = false;
    private Direction[] directions = { Direction.E, Direction.N, Direction.W, Direction.S };
    private ResponseCenter responseCenter;
    private int i = 0;
    private final Logger logger = LogManager.getLogger();

    public IslandPatrol(Drone drone, CommandCenter commandCenter, Island island) {
        super(drone, commandCenter, island);
        this.responseCenter = new ResponseCenter(drone, island, drone.getGPS());
    }

    @Override
    public void executeStep() { // assume we are already in the center of the island
        drone.scan(commandCenter);
        if (i % 3 ==0){
            System.out.println("Scans");
            drone.scan(commandCenter);
            i++;
            return;
        }
        else if (i % 3 ==1){
            drone.turnDrone(Direction.S, commandCenter);
            System.out.println("Turns");
            i++;
            return;
        }
        else if (i % 3 ==2){
            logger.info("MOVES");
            drone.moveForward(commandCenter);
            System.out.println("Moves");
            i++;
            phaseCompleted = true;
            return;
        }
    }
}