package ca.mcmaster.se2aa4.island.team09;

// propellers class controls which action is called
public class Propellers {
    private PropellerAction action;

    // calls the corresponding realization to perform the action
    public void turnDrone(String turnDirection, GPS gps, CommandCenter commands) {
        action = new Turn(gps, commands, turnDirection);
        action.performAction(); 
    } 

    public void moveForward(CommandCenter commands) {
        action = new Fly(commands);
        action.performAction();
    }

    public void stopDrone(CommandCenter commands) {
        action = new Stop(commands);
        action.performAction();
    }
} 