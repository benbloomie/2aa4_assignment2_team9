package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class DroneState {
    private GPS gps;
    private Propellers propellers;
    private Battery battery;
    private CommandCenter commands;

    public DroneState(String direction, Battery battery, Coordinate coordinates) {
        this.gps = new GPS(Direction.valueOf(direction), coordinates);
        this.commands = new CommandCenter();
        this.propellers = new Propellers();
        this.battery = battery;
    }

    public Direction getDirection() {
        return gps.getDirection();
    }

    public GPS getGPS() {
        return gps;
    }

    public void turnDrone(String newDirection) {
        propellers.turnDrone(newDirection, gps, commands);
    }

    public int getBatteryLevel() {
        return battery.getBattery();
    }

    public void consumeBattery(int batteryUsed) {
        battery.consumeBattery(batteryUsed);
    }

    public boolean isDroneDead() {
        return battery.isBatteryEmpty();
    }

    public void moveForward() {
        propellers.moveForward(commands);
    }

    public void stopDrone() {
        propellers.stopDrone(commands);
    }
    
    public JSONObject getDecision() {
        return commands.getNextCommand();  // retrieves action from commands queue to perform the corresponding action
    }

    public boolean isDroneMoving() {
        return commands.isDroneInMotion();
    }
}