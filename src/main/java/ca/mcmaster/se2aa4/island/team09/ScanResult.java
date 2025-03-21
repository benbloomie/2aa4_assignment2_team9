package ca.mcmaster.se2aa4.island.team09;

import java.util.List;

public class ScanResult {
    private List<String> creeks;
    private String site;

    public ScanResult(List<String> creeks, String site) {
        this.creeks = creeks;
        this.site = site;
    }

    public List<String> getCreeks() {
        return creeks;
    }

    public String getSite() {
        return site;
    }

    public boolean hasCreeks() {
        return creeks != null && !creeks.isEmpty();
    }

    public boolean hasSite() {
        return site != null;
    }
}