package ca.mcmaster.se2aa4.island.team09;

public class Creek {
    private String uId;
    private double x;
    private double y;

    public Creek(String uId, double x, double y) {
        this.uId = uId;
        this.x = x;
        this.y = y;
    }
    
    public double getDistanceTo(double startingX, double startingY) {
        // calculates the distance to each creek using x and y positions --> cannot fly on a diagonal
        double xDistance = this.x - startingX;
        double yDistance = this.y - startingY;
        return xDistance + yDistance;
    }

    public String getUId() {
        return uId;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    
}
