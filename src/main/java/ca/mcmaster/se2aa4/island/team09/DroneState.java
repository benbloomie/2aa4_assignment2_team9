package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class DroneState {
    private GPS gps;
    private Propellers propellers;
    private Battery battery;
    private CommandCenter commands;

    public DroneState(String direction, Battery battery) {
        this.gps = new GPS(Direction.valueOf(direction));
        this.commands = new CommandCenter();
        this.propellers = new Propellers(this.gps, commands);
        this.battery = battery;
    }

    public Direction getDirection() {
        return gps.getDirection();
    }

    public void turnDrone(String newDirection) {
        propellers.turnDrone(newDirection);
    }

    public void turnRight() {
        String turnDirection = gps.getRightDirection().toString();
        propellers.turnDrone(turnDirection);
    }

    public void turnLeft() {
        String turnDirection = gps.getLeftDirection().toString();
        propellers.turnDrone(turnDirection);
    }

    public int getBatteryLevel() {
        return battery.getBattery();
    }

    public void useBattery(int batteryUsed) {
        battery.updateBattery(batteryUsed);
    }

    public boolean isDroneDead() {
        return battery.isBatteryEmpty();
    }

    public void moveForward() {
        propellers.moveForward();
    }

    public void stopDrone() {
        propellers.stopDrone();
    }
    
    public JSONObject getDecision() {
        return commands.getNextCommand();  // retrieves action from commands queue to perform the corresponding action
    }

    public boolean isDroneMoving() {
        return commands.isDroneInMotion();
    }
}