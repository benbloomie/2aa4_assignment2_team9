package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class Fly implements Action {
    private final CommandCenter commands;

    public Fly(CommandCenter commands) {
        this.commands = commands; 
    }

    @Override
    public void performAction() {
        JSONObject decision = new JSONObject();
        // adds the fly action to the queue of commands
        decision.put("action", "fly");  
        commands.addCommand(decision);
    }
}
