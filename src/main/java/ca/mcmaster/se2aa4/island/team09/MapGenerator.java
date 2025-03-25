package ca.mcmaster.se2aa4.island.team09;

public class MapGenerator extends SearchPhase {

    public MapGenerator(Drone drone, CommandCenter commandCenter, Map map) {
        super(drone, commandCenter, map);
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

    private boolean isXNotSet() {
        return map.getX() == -1;
    }

    private boolean isYNotSet() {
        return map.getY() == -1;
    } 
}