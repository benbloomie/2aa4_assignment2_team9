package ca.mcmaster.se2aa4.island.team09;

public class PhotoScanner {

    public void scanGround(CommandCenter commandCenter) {
        Action action = new Scan(commandCenter);
        action.performAction();
    }

}