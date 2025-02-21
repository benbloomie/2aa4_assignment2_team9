package ca.mcmaster.se2aa4.island.team09;

public class DroneState {
    private Direction direction;
    private Battery battery;

    public DroneState(Direction direction, Battery battery) {
        this.direction = direction;
        this.battery = battery;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getBatteryLevel() {
        return battery.getBattery();
    }

}
