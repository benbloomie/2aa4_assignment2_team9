package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

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
