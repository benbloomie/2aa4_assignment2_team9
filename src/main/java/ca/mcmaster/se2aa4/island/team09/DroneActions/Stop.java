package ca.mcmaster.se2aa4.island.team09.DroneActions;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team09.MissionControl.CommandCenter;

public class Stop implements Action {
    private final CommandCenter commands;

    public Stop(CommandCenter commands) {
        this.commands = commands; 
    }

    @Override
    public void performAction() {
        JSONObject decision = new JSONObject();
        // adds the stop action to the queue of commands
        decision.put("action", "stop");   
        commands.addCommand(decision);
    }
}

