package ca.mcmaster.se2aa4.island.team09.DroneActions;

import ca.mcmaster.se2aa4.island.team09.Drone.Direction;
import ca.mcmaster.se2aa4.island.team09.MissionControl.CommandCenter;

public class Radar{

    public void echo(Direction echoDirection, CommandCenter commands){ // find the echo value from the nose, and send it to the command center
        Action action = new Echo(echoDirection, commands);
        action.performAction();
    }
}