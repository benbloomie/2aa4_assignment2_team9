package ca.mcmaster.se2aa4.island.team09;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PoIStorage {
    private EmergencySite emergencySite;
    private List<Creek> creeks = new ArrayList<>();
    private final Logger logger = LogManager.getLogger();

    public void addCreek(Creek foundCreek) {
        boolean isDuplicate = false;
        for (Creek creek : creeks) {
            if (creek.getId().equals(foundCreek.getId())) {
                isDuplicate = true;
                break;
            }
        }
        if (!isDuplicate) {
            creeks.add(foundCreek);
        }
    }

    public String getCreekResults() {
        StringBuffer creekIds = new StringBuffer();
        creekIds.append("[");

        for (int i = 0; i < creeks.size(); i++) {
            Creek creek = creeks.get(i);
            creekIds.append(creek.getId());

            if (i < creeks.size() - 1) { // add a comma between id's until we reach the last string
                creekIds.append(", ");
            }
        }
        creekIds.append("]");
        return creekIds.toString();
    }

    public void setEmergencySite(EmergencySite emergencySite) {
        this.emergencySite = emergencySite;
    }

    public EmergencySite getEmergencySite() {
        return emergencySite;
    }

    public Creek findNearestCreek(double startingX, double startingY) {
        double minDistance = Double.MAX_VALUE;
        Creek nearestCreek = null;

        for (Creek creek : creeks) {
            double distanceToCreek = creek.getDistanceTo(startingX, startingY);
            logger.info("Creek " + creek.getId() + " is " + distanceToCreek + " away from site");

            if (distanceToCreek < minDistance) {
                minDistance = distanceToCreek;
                nearestCreek = creek;
            }
        }
        return nearestCreek;
    }
}