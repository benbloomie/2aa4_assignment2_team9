package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class RadarManager {
    private int radarRange;
    private EchoStatus found;
    private List<Integer> radarRanges;
    private List<EchoStatus> radarFounds;

    public RadarManager() {
        this.radarRanges = new ArrayList<>();
        this.radarFounds = new ArrayList<>();
    }
    
    public void updateStatus(JSONObject extras){
        radarRange = extras.getInt("range");  // sets the range to the found type 
        String foundString = extras.getString("found");
        found = EchoStatus.valueOf(foundString);

        radarRanges.add(radarRange);
        radarFounds.add(found);
    }


    public int getRangeStatus(int index) {
        return radarRanges.get(index);
    }

    public EchoStatus getEchoStatus(int index) {
        return radarFounds.get(index);
    }

    
}
