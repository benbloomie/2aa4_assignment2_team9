package ca.mcmaster.se2aa4.island.team09;

public class IslandPatrol extends SearchPhase{
    private int segmentLength = 1;

    public IslandPatrol(DroneState drone, CommandCenter commandCenter, ResponseCenter responseCenter, Island island){
        super(drone, commandCenter, responseCenter, island);
    }
    @Override
    private void executeStep(){ //assume we are already in the center of the island
        //call the east north west south segments in that order with the lengths extending each time to create a square scan radiating from the center of the island
    }
    
    private void eastSegment(){
        drone.turnDrone(Direction.E, commandCenter);
        drone.moveForward(commandCenter);
    }

    private void westSegment(){
        drone.turnDrone(Direction.W, commandCenter);
        drone.moveForward(commandCenter);
    }

    private void northSegment(){
        drone.turnDrone(Direction.N, commandCenter);
        drone.moveForward(commandCenter);
    }

    private void southSegment(){
        drone.turnDrone(Direction.S, commandCenter);
        drone.moveForward(commandCenter);
    }
    
}
