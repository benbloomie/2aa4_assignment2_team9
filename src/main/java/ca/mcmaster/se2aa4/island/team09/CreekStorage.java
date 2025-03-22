package ca.mcmaster.se2aa4.island.team09;

import java.util.ArrayList;
import java.util.List;

public class CreekStorage {

    private List<Creek> creeks = new ArrayList<>();

    public void addCreek(Creek creek) {
        if (!creeks.contains(creek)) {
            creeks.add(creek);
        }
    }

    public List<Creek> getAllCreeks() {
        return creeks;
    }

    public boolean isEmpty() {
        return creeks.isEmpty();
    }

    /* 
    public Creek findNearestCreek(double startingX, double startingY) {
        double minDistance = Double.MAX_VALUE;
        Creek nearestCreek = null;

        for (Creek creek : creeks) {
            double distanceToCreek = creek.getDistanceTo(startingX, startingY);
            if (distanceToCreek < minDistance) {
                minDistance = distanceToCreek;
                nearestCreek = creek;
            }
        }
        return nearestCreek;
    }
        */
}