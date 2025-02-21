package ca.mcmaster.se2aa4.island.team09;
import java.util.*;

public enum Direction {
    N,
    E,
    S,
    W;

    // returns an immutable list, so the directions cannot be changed
    public List<Direction> getDirections() {
        return List.of(values());
    }
}