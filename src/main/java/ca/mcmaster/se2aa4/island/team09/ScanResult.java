// NOT USED ANYMORE --> DONT NEED

package ca.mcmaster.se2aa4.island.team09;

import java.util.List;

public class ScanResult {
    private List<Creek> creeks;
    private String site;

    public ScanResult(List<Creek> creeks, String site) {
        this.creeks = creeks;
        this.site = site;
    }

    public List<Creek> getCreeks() {
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