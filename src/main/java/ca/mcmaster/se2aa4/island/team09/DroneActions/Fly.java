package ca.mcmaster.se2aa4.island.team09.DroneActions;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team09.Drone.GPS;
import ca.mcmaster.se2aa4.island.team09.MissionControl.CommandCenter;

public class Fly implements Action {
    private final CommandCenter commands;
    private final GPS gps;

    public Fly(GPS gps, CommandCenter commands) {
        this.commands = commands; 
        this.gps = gps;
    }

    @Override
    public void performAction() {
        JSONObject decision = new JSONObject();
        // adds the fly action to the queue of commands
        decision.put("action", "fly");  
        gps.updateCoordinates();
        commands.addCommand(decision);
    }
}
