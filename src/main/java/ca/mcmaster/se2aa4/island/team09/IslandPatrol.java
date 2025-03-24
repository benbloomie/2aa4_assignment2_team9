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
            drone.turnDrone(drone.getGPS().getLeftDirection(), commandCenter); //turn drone left
            
            moveCounter++;
        } else {
            drone.moveForward(commandCenter);
            moveCounter++;
        }

        if (moveCounter > segmentLength) { //segment has been completed by drone so move to create next segment
            stepCounter++;
            moveCounter = 0;
            if (stepCounter % 2 == 0) { //extend length of segment by 1 every other segment that is traversed
                segmentLength++;
            }
        }

        if (segmentLength >= map.getX() - 1){ //if drone is going to spiral out of map end the phase and stop the drone
            phaseCompleted = true;
            drone.stop(commandCenter);
            
        }
        else if (drone.getBatteryLevel() < 100){ //stop the drone if battery runs too low
            drone.stop(commandCenter);
        }
        
    }
}