package ca.mcmaster.se2aa4.island.team09.Drone;

import ca.mcmaster.se2aa4.island.team09.DroneActions.Action;
import ca.mcmaster.se2aa4.island.team09.DroneActions.Fly;
import ca.mcmaster.se2aa4.island.team09.DroneActions.Stop;
import ca.mcmaster.se2aa4.island.team09.DroneActions.Turn;
import ca.mcmaster.se2aa4.island.team09.MissionControl.CommandCenter;

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
