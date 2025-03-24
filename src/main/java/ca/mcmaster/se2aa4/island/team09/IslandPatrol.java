package ca.mcmaster.se2aa4.island.team09;

public class IslandPatrol extends SearchPhase {
    private int stepCounter;
    private int moveCounter; // progress towards completion of a segment of the algorithm
    private int segmentLength = 1;
    private boolean shouldScan = false;
    private ResponseCenter responseCenter;

    public IslandPatrol(Drone drone, CommandCenter commandCenter, Island island) {
        super(drone, commandCenter, island);
        this.responseCenter = new ResponseCenter(drone, island, drone.getGPS());
    }

    @Override
    public void executeStep() { // assume we are already in the center of the island
        // Check if both emergency site and creek are found

        drone.scan(commandCenter);
        int directionIndex = stepCounter % 4;
        if (moveCounter == 0) {
            drone.turnDrone(drone.getGPS().getLeftDirection(), commandCenter);
            
            moveCounter++;
        } else {
            drone.moveForward(commandCenter);
            moveCounter++;
        }

        if (moveCounter > segmentLength) {
            stepCounter++;
            moveCounter = 0;
            if (stepCounter % 2 == 0) {
                segmentLength++;
            }
        }
        if (segmentLength >= island.getX() - 7){
            phaseCompleted = true;
        }
        
    }
}