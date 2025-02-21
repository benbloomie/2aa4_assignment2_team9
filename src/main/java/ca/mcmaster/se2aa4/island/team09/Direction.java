package ca.mcmaster.se2aa4.island.team09;

public enum Direction {
    N,
    E,
    S,
    W;

    public Direction[] getDirections() {
        return values().clone();
    }
}