package ca.mcmaster.se2aa4.island.team09;

public class DroneState {
    private DirectionManager directionManager;
    private Battery battery;

    public DroneState(Direction direction, Battery battery) {
        this.directionManager = new DirectionManager(direction);
        this.battery = battery;
    }

    public Direction getDirection() {
        return directionManager.getDirection();
    }

    public void turnDrone(String newDirection) {
        directionManager.turnDrone(Direction.valueOf("W"));
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