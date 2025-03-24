package ca.mcmaster.se2aa4.island.team09;

public class IslandPatrol extends SearchPhase {
    private int stepCounter;
    private int moveCounter; // progress towards completion of a segment of the algorithm
    private int segmentLength = 1;

    public IslandPatrol(Drone drone, CommandCenter commandCenter, Map map) {
        super(drone, commandCenter, map);
    }

    @Override
    public void executeStep() { // assume we are already in the center of the island
        // Check if both emergency site and creek are found

        drone.scan(commandCenter);
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
        if (segmentLength >= map.getX() - 1){
            phaseCompleted = true;
            drone.stop(commandCenter);
            
        }
        
    }
}