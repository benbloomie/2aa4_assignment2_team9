package ca.mcmaster.se2aa4.island.team09;

public class EmergencySite {
    private String Id;
    private int x;
    private int y;

    public EmergencySite(String Id, int x, int y) {
        this.Id = Id;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return Id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }   
}