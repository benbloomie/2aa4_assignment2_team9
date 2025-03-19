package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class RadarStatus {
    private int range;
    private EchoStatus found;

    public RadarStatus() {
        this.found = EchoStatus.OUT_OF_RANGE;
    }
    
    public void updateStatus(JSONObject extras){
        range = extras.getInt("range");  // sets the range to the found type 
        String foundString = extras.getString("found");
        found = EchoStatus.valueOf(foundString);
    }

    public int getRange() { //return the range
        return range;
    }

    public EchoStatus getEcho() { //return the most recent echoStatus
        return found;
    }
    
}
