package ca.mcmaster.se2aa4.island.team09;

public class IslandPatrol extends SearchPhase{
    private int stepCounter;
    private int moveCounter; //progress towards completion of a segment of the algorithm
    private int segmentLength = 1;
    private Direction[] directions = {Direction.E, Direction.N, Direction.W, Direction.S}

    public IslandPatrol(DroneState drone, CommandCenter commandCenter, ResponseCenter responseCenter, Island island){
        super(drone, commandCenter, responseCenter, island);
    }
    @Override
    public void executeStep(){ //assume we are already in the center of the island
        //call the east north west south segments in that order with the lengths extending each time to create a square scan radiating from the center of the island
        int directionIndex = stepCounter%4;
        if (moveCounter == 0){
            drone.turnDrone(directions[directionIndex], commandCenter);
            moveCounter++;
        }
        else{
            drone.moveForward(commandCenter);
            moveCounter++;
        }
        
        if (moveCounter > segmentLength){
            stepCounter++;
            moveCounter = 0;
            if (stepCounter % 2 == 0){
                segmentLength++;
            }
        }

    }
}