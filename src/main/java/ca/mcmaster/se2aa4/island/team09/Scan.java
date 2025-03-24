package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class Scan implements Action {
    private CommandCenter commands;

    public Scan(CommandCenter commandCenter){
        this.commands = commandCenter;
    }

    @Override
    public void performAction() {
        // creates a JSONObject to register the action 
        JSONObject decision = new JSONObject();
        decision.put("action", "scan");
        commands.addCommand(decision);  // adds action to commands queue
    }
}
