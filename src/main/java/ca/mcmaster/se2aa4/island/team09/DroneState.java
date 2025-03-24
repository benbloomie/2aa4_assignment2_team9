package ca.mcmaster.se2aa4.island.team09;

public class DroneState {
    private GPS gps;
    private Propellers propellers;
    private Battery battery;
    private Radar radar;
    private PhotoScanner photoScanner;

    public DroneState(String direction, Battery battery, Coordinate coordinates) {
        this.gps = new GPS(Direction.valueOf(direction), coordinates);
        this.propellers = new Propellers();
        this.battery = battery;
        this.radar = new Radar();
        this.photoScanner = new PhotoScanner();
    }

    public Direction getDirection() {
        return gps.getDirection();
    }

    public GPS getGPS() {
        return gps;
    }

    public void turnDrone(Direction newDirection, CommandCenter commands) {
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

    public void moveForward(CommandCenter commands) {
        propellers.moveForward(gps, commands);
    }

    public void stopDrone(CommandCenter commands) {
        propellers.stopDrone(commands);
    }

    public void echo(Direction echoDirection, CommandCenter commands) {
        radar.echo(echoDirection, commands);
    }

    public void scan(CommandCenter commands) {
        photoScanner.scanGround(commands);
    }
}