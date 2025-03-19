package ca.mcmaster.se2aa4.island.team09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Battery {
    private final Logger logger = LogManager.getLogger();
    private int battery;
    
    public Battery(int battery) {
        // ensures that the battery is valid before proceeding
        if (battery < 0) {
            throw new IllegalArgumentException("ERROR. Battery cannot be negative.");
        }
        this.battery = battery;
    }

    public void consumeBattery(int amountUsed) {
        // ensures the amount used is not greater than what the drone has left
        if (battery >= amountUsed) {
            battery = battery - amountUsed;
        }
        else {
            battery = 0;  // if it does exceed its current charge, set it to zero to prevent a negative battery
        }
        logger.info("The battery of the drone after the action is: {}", getBattery());
    }

    public int getBattery() {
        return battery;
    }

    public boolean isBatteryEmpty() {
        return battery <= 0;
    }
}
