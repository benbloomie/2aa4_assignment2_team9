package ca.mcmaster.se2aa4.island.team09;

// propellers class controls which action is called
public class Propellers {
    private final Turn turnAction;
    private final Fly flyAction;
    private final Stop stopAction;

    public Propellers(GPS gps, CommandCenter commands) {
        this.turnAction = new Turn(gps, commands);
        this.flyAction = new Fly(commands);
        this.stopAction = new Stop(commands);
    }

    // calls the corresponding realization to perform the action
    public void turnDrone(String turnDirection) {
        turnAction.setTurnDirection(turnDirection);
        turnAction.performAction();    
    } 

    public void moveForward() {
        flyAction.performAction();
    }

    public void stopDrone() {
        stopAction.performAction();    
    }
} 