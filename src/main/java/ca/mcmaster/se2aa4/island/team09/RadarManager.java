package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;

public class RadarManager {
    private int range;
    private EchoStatus found;

    public RadarManager() {
        this.found = EchoStatus.OUT_OF_RANGE;
    }
    
    public void updateStatus(JSONObject extras){
        range = extras.getInt("range");  // sets the range to the found type 
        String foundString = extras.getString("found");
        found = EchoStatus.valueOf(foundString);
    }

    public int getRangeStatus() { //return the range
        return range;
    }

    public EchoStatus getEchoStatus() { //return the most recent echoStatus
        return found;
    }
    
}
