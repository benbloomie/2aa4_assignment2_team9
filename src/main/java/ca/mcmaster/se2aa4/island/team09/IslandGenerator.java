package ca.mcmaster.se2aa4.island.team09;

public class IslandGenerator extends SearchPhase {

    public IslandGenerator(DroneState drone, CommandCenter commandCenter, ResponseCenter responseCenter, Island island) {
        super(drone, commandCenter, responseCenter, island);
    }

    @Override
    public void executeStep() {
        // if x has not been set by initial scan, echo E
        if (isXNotSet()) {
            drone.echo(drone.getGPS().getDirection(), commandCenter);
        }
        // if y has not been set by initial scan, echo S
        else if (isYNotSet()) {
            if (drone.getGPS().getDirection().ordinal() < 2) {   // handles right echos when facing N and E
                drone.echo(drone.getGPS().getRightDirection(), commandCenter);
            }
            else {
                drone.echo(drone.getGPS().getLeftDirection(), commandCenter);
            }
            phaseCompleted = true;  // once the islands x and y have been set, indicate the phase is complete
        }
    }

    public boolean isXNotSet() {
        return island.getX() == -1;
    }

    public boolean isYNotSet() {
        return island.getY() == -1;
    } 
}