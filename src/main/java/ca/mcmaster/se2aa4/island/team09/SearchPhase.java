package ca.mcmaster.se2aa4.island.team09;

public abstract class SearchPhase {
    protected Drone drone;
    protected CommandCenter commandCenter;
    protected Island island;
    protected boolean phaseCompleted;

    // constructor to initialize shared attributes across search phases
    public SearchPhase(Drone drone, CommandCenter commandCenter, Island island) {
        this.drone = drone;
        this.commandCenter = commandCenter;
        this.island = island;
        this.phaseCompleted = false;
    }

    public abstract void executeStep();

    public boolean isActionComplete() {
        return phaseCompleted;
    }
}