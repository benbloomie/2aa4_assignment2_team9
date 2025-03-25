package ca.mcmaster.se2aa4.island.team09.MissionControl;

import ca.mcmaster.se2aa4.island.team09.Drone.Drone;
import ca.mcmaster.se2aa4.island.team09.Environment.Map;

public abstract class SearchPhase {
    protected Drone drone;
    protected CommandCenter commandCenter;
    protected Map map;
    protected boolean phaseCompleted;

    // constructor to initialize shared attributes across search phases
    public SearchPhase(Drone drone, CommandCenter commandCenter, Map map) {
        this.drone = drone;
        this.commandCenter = commandCenter;
        this.map = map;
        this.phaseCompleted = false;
    }

    public abstract void executeStep();

    public boolean isActionComplete() {
        return phaseCompleted;
    }
}