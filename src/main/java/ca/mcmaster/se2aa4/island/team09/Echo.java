package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class Echo implements Action{
    private Direction echoDirection;
    private CommandCenter commands;

    public Echo(Direction echoDirection, CommandCenter commandCenter){
        this.echoDirection = echoDirection;
        this.commands = commandCenter;
    }

    public void performAction() { // find the echo value and send it to the command center
        // creates a JSONObject to store the parameter information
        JSONObject parameters = new JSONObject();
        parameters.put("direction", echoDirection);

        // creates a JSONObject to register the action and its corresponding parameters
        JSONObject decision = new JSONObject();
        decision.put("action", "echo");
        decision.put("parameters", parameters);
        commands.addCommand(decision);  // adds action to commands queue
    }
}
