package ca.mcmaster.se2aa4.island.team09;

public class IslandLocater extends SearchPhase {
    private int movesMadeToMiddle;
    private int movesMadeToIsland;
    private int rangeToGround;

    public IslandLocater(DroneState drone, CommandCenter commandCenter, ResponseCenter responseCenter, Island island) {
        super(drone, commandCenter, responseCenter, island);
        this.movesMadeToMiddle = 0;
        this.movesMadeToIsland = 0;
    }

    @Override
    public void executeStep() {
        // moves drone forward to middle position
        if (movesMadeToMiddle < island.getX()/2 - 1) {
            moveToMiddle();
            return;
        }
        // turns drone when it reaches the middle of the map
        else if (movesMadeToMiddle == island.getX()/2 - 1) {
            faceIsland();
            return;
        }

        setRangeToGround();
        // moves drone forward until it reaches GROUND 
        if (movesMadeToIsland < rangeToGround) {
            moveToIsland();
            return;
        }
        // phase is completed
        drone.turnDrone(Direction.E, commandCenter);
        phaseCompleted = true;
    }

    private void moveToMiddle() {
        drone.moveForward(commandCenter);
        movesMadeToMiddle++;
    }

    private void faceIsland() {
        // updates the drone to face the island; scans to get distance to island
        drone.turnDrone(Direction.S, commandCenter);
        drone.frontEcho(commandCenter);
        movesMadeToMiddle++;
    }

    private void moveToIsland() {
        drone.moveForward(commandCenter);
        movesMadeToIsland++;
    }

    private void setRangeToGround() {
        // now only makes one assignment to the range to GROUND
        if (rangeToGround == 0) {
            this.rangeToGround = responseCenter.getRadarStatus().getRange(); 
        }
    }
}