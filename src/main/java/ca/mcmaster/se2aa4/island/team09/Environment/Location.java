package ca.mcmaster.se2aa4.island.team09.Environment;

public abstract class Location {
    protected String id;
    protected int x;
    protected int y;

    public Location(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    
    public String getId() {
        return id;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
}
