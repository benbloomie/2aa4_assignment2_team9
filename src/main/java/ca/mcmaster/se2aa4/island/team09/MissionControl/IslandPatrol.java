package ca.mcmaster.se2aa4.island.team09.MissionControl;

import ca.mcmaster.se2aa4.island.team09.Drone.Drone;
import ca.mcmaster.se2aa4.island.team09.Environment.Map;

public class IslandPatrol extends SearchPhase {
    private int stepCounter;
    private int moveCounter; // progress towards completion of a segment of the algorithm
    private int segmentLength = 1;

    public IslandPatrol(Drone drone, CommandCenter commandCenter, Map map) {
        super(drone, commandCenter, map);
    }

    @Override
    public void executeStep() { // assumes we are already in the center of the island
        drone.scan(commandCenter);

        if (moveCounter == 0) {
            startSegment();
        }
        else {
            continueSegment();
        }

        if (moveCounter > segmentLength) {
            completeSegment();
        }
        finishPhaseCheck();
    }
    
    private void startSegment() {
        drone.turnDrone(drone.getGPS().getLeftDirection(), commandCenter);
        moveCounter++;
    }
    
    private void continueSegment() {
        drone.moveForward(commandCenter);
        moveCounter++;
    }
    
    private void completeSegment() { //segment has been completed by drone so move to create next segment
        stepCounter++;
        moveCounter = 0;
        if (stepCounter % 2 == 0) { //extend length of segment by 1 every other segment that is traversed
            segmentLength++;
        }
    }
    
    private void finishPhaseCheck() { // if drone is going to spiral out of map or battery is nearly depleted end the phase and stop the drone
        if ((segmentLength >= map.getX() - 1) || (drone.getBatteryLevel() < 100)) { 
            phaseCompleted = true;
            drone.stop(commandCenter);
        }
    }
}
