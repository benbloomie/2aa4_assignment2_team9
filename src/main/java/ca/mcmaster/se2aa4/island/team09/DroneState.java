package ca.mcmaster.se2aa4.island.team09;

public class DroneState {
    private DirectionManager directionManager;
    private Battery battery;

    public DroneState(String direction, Battery battery) {
        this.directionManager = new DirectionManager(Direction.valueOf(direction));
        this.battery = battery;
    }

    public Direction getDirection() {
        return directionManager.getDirection();
    }

    public void turnDrone(String newDirection) {
        directionManager.turnDrone(newDirection);
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

}