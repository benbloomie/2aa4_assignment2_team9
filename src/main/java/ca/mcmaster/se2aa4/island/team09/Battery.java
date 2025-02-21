package ca.mcmaster.se2aa4.island.team09;

public class Battery {
    private Integer battery;
    
    public Battery(Integer battery) {
        if (battery < 0) {
            throw new IllegalArgumentException("ERROR. Battery cannot be negative.");
        }
        this.battery = battery;
    }

    public void updateBattery(int amountUsed) {
        // checking the amount used against the current battery charge prevents the battery from going negative
        if (this.battery >= amountUsed) {
            this.battery = this.battery - amountUsed;
        }
        else {
            this.battery = 0;
        }
    }

    public int getBattery() {
        return this.battery;
    }

    public boolean isBatteryEmpty() {
        return battery <= 0;
    }
}