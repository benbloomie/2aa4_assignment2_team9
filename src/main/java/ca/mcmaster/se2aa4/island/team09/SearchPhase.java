package ca.mcmaster.se2aa4.island.team09;

public abstract class SearchPhase {
    protected DroneState drone;
    protected CommandCenter commandCenter;
    protected ResponseCenter responseCenter;
    protected Island island;
    protected boolean phaseCompleted;

    // constructor to initialize shared attributes across search phases
    public SearchPhase(DroneState drone, CommandCenter commandCenter, ResponseCenter responseCenter, Island island) {
        this.drone = drone;
        this.commandCenter = commandCenter;
        this.responseCenter = responseCenter;
        this.island = island;
        this.phaseCompleted = false;
    }

    public abstract void executeStep();

    public boolean isActionComplete() {
        return phaseCompleted;
    }
}