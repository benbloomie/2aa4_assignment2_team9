package ca.mcmaster.se2aa4.island.team09;

public class Battery {
    private int battery;
    
    public Battery(int battery) {
        // ensures that the battery is valid before proceeding
        if (battery < 0) {
            throw new IllegalArgumentException("ERROR. Battery cannot be negative.");
        }
        this.battery = battery;
    }

    public void updateBattery(int amountUsed) {
        // ensures the amount used is not greater than what the drone has left
        if (this.battery >= amountUsed) {
            this.battery = this.battery - amountUsed;
        }
        else {
            this.battery = 0;  // if it does exceed its current charge, set it to zero to prevent a negative battery
        }
    }

    public int getBattery() {
        return this.battery;
    }

    public boolean isBatteryEmpty() {
        return battery <= 0;
    }
}