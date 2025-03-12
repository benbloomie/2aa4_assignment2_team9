package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;
import java.util.ArrayList;

public class RadarManager {
    private JSONObject extras;
    private int range;
    private EchoStatus found;
    private ArrayList<Object> radarRanges;
    private ArrayList<Object> radarFounds;
    private int radarLength;
    private ArrayList<Object> radarPair;
    

    

    public void setJSON(JSONObject extras){
        this.extras = extras;
    }


    //Direction.valueOf(input);

    currentStatus(){

    }

    updateStatus(){
        range = extras.getInt("range");
        String foundString = new String("");
        foundString = extras.getString("found");
        found = EchoStatus.valueOf(foundString);

        radarRanges.add(range);
        radarFounds.add(found);
    }

    public ArrayList<Object> getStatus(){
        radarRanges.add(range);
        radarFounds.add(found);
        return radar
    }
    
}
