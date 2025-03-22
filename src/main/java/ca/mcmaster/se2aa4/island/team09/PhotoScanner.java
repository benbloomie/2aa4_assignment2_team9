package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class PhotoScanner {

    public ScanResult scan(String scannerResponse) {
        JSONObject scannerJson = new JSONObject(scannerResponse);
        
        
        JSONObject extras = scannerJson.optJSONObject("extras");
        if (extras == null) {
            extras = new JSONObject();
        }

        List<Creek> creeks = new ArrayList<>();
        
        if (extras.has("creeks")) {
            JSONArray creeksArray = extras.getJSONArray("creeks");
            for (int i = 0; i < creeksArray.length(); i++) {
                String creekString = creeksArray.getString(i);
                Creek creek = new Creek(creekString); // Assuming such a constructor exists
                creeks.add(creek);
            }
        }

        String site = null;
        if (extras.has("sites")) {
            JSONArray sitesArray = extras.getJSONArray("sites");
            if (sitesArray.length() > 0) {
                site = sitesArray.getString(0);
            }
        }

        return new ScanResult(creeks, site);
    }
}