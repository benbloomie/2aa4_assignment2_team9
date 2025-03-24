package ca.mcmaster.se2aa4.island.team09;

public class Creek {
    private String Id;
    private int x;
    private int y;

    // public Creek(String Id, double x, double y)
    public Creek(String Id, int x, int y) {
        this.Id = Id;
        this.x = x;
        this.y = y;
    }
    
     
    public double getDistanceTo(double startingX, double startingY) {
        return Math.abs(this.x - startingX) + Math.abs(this.y - startingY);
    }
    

    public String getId() {
        return Id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }    
}