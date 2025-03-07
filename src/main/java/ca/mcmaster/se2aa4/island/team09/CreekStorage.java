package ca.mcmaster.se2aa4.island.team09;

import java.util.HashMap;
import java.util.Map;

public class CreekStorage {
    private final Map<String, Creek> creeks;

    public CreekStorage() {
        this.creeks = new HashMap<>();
    }

    public void storeCreek() {
        // NOT SURE HOW TO ACCESS POIS FILE
        //creeks.put(uId, new Creek(uId, x, y));
    }

    public Creek findNearestCreek(double startingX, double startingY) {
        double minDistance = Double.MAX_VALUE;
        Creek nearestCreek = null;

        // iterates through each creek, comparing the total distance to them
        for (Creek creek: creeks.values()) {
            double distanceToCreek = creek.getDistanceTo(startingX, startingY);
            if (distanceToCreek < minDistance) {
                // update information if a closer creek is located
                minDistance = distanceToCreek;
                nearestCreek = creek;
            }
        }
        return nearestCreek;
    }
}