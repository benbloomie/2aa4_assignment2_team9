package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class RadarManager {
    private int radarRange;
    private EchoStatus found;
    private List<Object> radarRanges;
    private List<Object> radarFounds;
    private List<Object> radarPair;

    public RadarManager() {
        this.radarRanges = new ArrayList<>();
        this.radarFounds = new ArrayList<>();
        this.radarPair = new ArrayList<>();
    }
    
    public void updateStatus(JSONObject extras){
        radarRange = extras.getInt("range");  // sets the range to the found type (GROUND or OUT_OF_RANGE)
        String foundString = extras.getString("found");
        found = EchoStatus.valueOf(foundString);

        radarRanges.add(radarRange);
        radarFounds.add(found);
    }

    public List<Object> getStatus(int index){
        radarPair.clear();
        radarPair.add(radarRanges.get(index));
        radarPair.add(radarFounds.get(index));
        return radarPair;
    }
    
}
