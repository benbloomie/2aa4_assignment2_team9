package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class DroneState {
    private GPS gps;
    private Propellers propellers;
    private Battery battery;

    public DroneState(String direction, Battery battery) {
        this.gps = new GPS(Direction.valueOf(direction));
        this.propellers = new Propellers(this.gps);
        this.battery = battery;
    }

    public Direction getDirection() {
        return gps.getDirection();
    }

    public void turnDrone(String newDirection) {
        propellers.turnDrone(newDirection);
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
    
    public JSONObject getDecision() {
        return propellers.getMovements();
    }

    public boolean isDroneMoving() {
        return propellers.inUTurn();
    }

}