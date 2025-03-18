package ca.mcmaster.se2aa4.island.team09;

import java.util.List;

public class ScanResult {
    private final int cost;
    private final List<String> biomes;
    private final List<String> creeks;
    private final List<String> sites;

    public ScanResult(int cost, List<String> biomes, List<String> creeks, List<String> sites) {
        this.cost = cost;
        this.biomes = biomes;
        this.creeks = creeks;
        this.sites = sites;
    }

    public int getCost() {
        return cost;
    }

    public List<String> getBiomes() {
        return biomes;
    }

    public List<String> getCreeks() {
        return creeks;
    }

    public List<String> getSites() {
        return sites;
    }
}