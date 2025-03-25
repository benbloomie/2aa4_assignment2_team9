package ca.mcmaster.se2aa4.island.team09.MissionControl;

import org.json.JSONObject;
import java.util.Queue;
import java.util.LinkedList;

public class CommandCenter {
    private Queue<JSONObject> commands;  // stores all of the drones movements

    public CommandCenter() {
        this.commands = new LinkedList<>();
    }

    public void addCommand(JSONObject command) {
        commands.add(command);
    }

    public JSONObject getNextCommand() {
        return commands.poll();  // returns and removes the command from the queue to compute the action
    }

    public boolean isDroneInAction() {
        return !commands.isEmpty();  // checks if the queue is empty to determine if the drone is in a U-Turn
    }
}