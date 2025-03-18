package ca.mcmaster.se2aa4.island.team09;

import java.util.ArrayList;
import java.util.List;

public class ScanManager {
    private final List<ScanResult> scanHistory;

    public ScanManager() {
        this.scanHistory = new ArrayList<>();
    }

    public void storeScanResult(ScanResult result) {
        scanHistory.add(result);
    }

    public ScanResult getScanAtIndex(int index) {
        if (index < scanHistory.size()) {
            return scanHistory.get(index);
        } 

        else {
            return null;
        }
    }

    public int getTotalScans() {
        return scanHistory.size();
    }
}