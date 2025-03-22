package ca.mcmaster.se2aa4.island.team09;

public class IslandPatrol extends SearchPhase{
    private int segmentLength = 1;

    public IslandPatrol(DroneState drone, CommandCenter commandCenter, ResponseCenter responseCenter, Island island){
        super(drone, commandCenter, responseCenter, island);
    }
    @Override
    private void executeStep(){
        //call the east north west south segments in that order with the lengths extending each time to create a square scan radiating from the center of the island
    }

    private void eastSegment(){

    }

    private void westSegment(){

    }

    private void northSegment(){

    }

    private void southSegment(){

    }
    
}
