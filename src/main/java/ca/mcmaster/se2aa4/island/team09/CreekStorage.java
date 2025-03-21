package ca.mcmaster.se2aa4.island.team09;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

public class CreekStorage {

    private Set<String> creekIds = new HashSet<>();

    public void addCreeks(List<String> creeks) {
        creekIds.addAll(creeks);
    }

    public Set<String> getAllCreekIds() {
        return creekIds;
    }

    public boolean isEmpty() {
        return creekIds.isEmpty();
    }

}

    public Creek findNearestCreek(double startingX, double startingY) {
        double minDistance = Double.MAX_VALUE;
        Creek nearestCreek = null;

        // iterates through each creek, comparing the total distance to them
        for (Creek creek : creeks.values()) {
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