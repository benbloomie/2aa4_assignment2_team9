package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

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

