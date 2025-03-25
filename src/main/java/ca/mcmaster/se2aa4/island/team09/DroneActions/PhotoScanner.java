package ca.mcmaster.se2aa4.island.team09.DroneActions;

import ca.mcmaster.se2aa4.island.team09.MissionControl.CommandCenter;

public class PhotoScanner {

    public void scanGround(CommandCenter commandCenter) {
        Action action = new Scan(commandCenter);
        action.performAction();
    }

}