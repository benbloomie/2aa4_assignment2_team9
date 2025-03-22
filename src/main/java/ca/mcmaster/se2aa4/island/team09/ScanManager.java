package ca.mcmaster.se2aa4.island.team09;

import java.util.ArrayList;
import java.util.List;

public class ScanManager {
    private List<ScanResult> scans = new ArrayList<>();

    public void addScan(ScanResult result) {
        scans.add(result);
    }

    public List<Creek> getAllCreekIds() {
        List<Creek> ids = new ArrayList<>();
        for (ScanResult scan : scans) {
            if (scan.hasCreeks()) {
                ids.addAll(scan.getCreeks());
            }
        }
        return ids;
    }

    public String getEmergencySiteId() {
        for (ScanResult scan : scans) {
            if (scan.hasSite()) {
                return scan.getSite();
            }
        }
        return null;
    }
}