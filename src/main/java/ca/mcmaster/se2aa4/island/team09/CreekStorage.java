package ca.mcmaster.se2aa4.island.team09;

import java.util.HashSet;
import java.util.Set;

public class CreekStorage {

    private Set<Creek> creeks = new HashSet<>();

    public void addCreek(Creek creek) {
        creeks.add(creek);  // Prevents duplicates based on equals/hashCode
    }

    public Set<Creek> getAllCreeks() {
        return creeks;
    }

    public boolean isEmpty() {
        return creeks.isEmpty();
    }

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
}