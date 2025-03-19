package ca.mcmaster.se2aa4.island.team09;

public class IslandLocater extends SearchPhase {
    private int movesMade;

    public IslandLocater(DroneState drone, CommandCenter commandCenter, ResponseCenter responseCenter, Island island) {
        super(drone, commandCenter, responseCenter, island);
        this.movesMade = 0;
    }

    @Override
    public void executeStep() {
        // moves drone forward to middle position
        if (movesMade < island.getX()/2 - 1) {
            drone.moveForward(commandCenter);
            movesMade++;
        }
        // turns drone when it reaches the middle of the map
        else if (movesMade == island.getX()/2 - 1) {
            drone.turnDrone(Direction.S, commandCenter);
            drone.frontEcho(commandCenter);
            movesMade++;
        }
        // moves drone forward until it reaches GROUND 
        else {
            int movesToIsland = responseCenter.getRadarStatus().getRange();
            for (int i = 0; i < movesToIsland; i++) {
                drone.moveForward(commandCenter);
            }
            drone.turnDrone(Direction.E, commandCenter);
            phaseCompleted = true;
        }
    }
}