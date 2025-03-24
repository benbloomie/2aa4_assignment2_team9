package ca.mcmaster.se2aa4.island.team09;

// propellers class controls which action is called
public class Propellers {

    // calls the corresponding realization to perform the action
    public void turnDrone(Direction turnDirection, GPS gps, CommandCenter commands) {
        Action action = new Turn(gps, commands, turnDirection);
        action.performAction(); 
    } 

    public void moveForward(GPS gps, CommandCenter commands) {
        Action action = new Fly(gps, commands);
        action.performAction();
    }

    public void stopDrone(CommandCenter commands) {
        Action action = new Stop(commands);
        action.performAction();
    }
} 
