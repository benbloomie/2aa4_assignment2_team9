package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.ArrayList;



public class PhotoScanner {
    
    //All mentions of scan will be replaced later with appropriate name.
    
    private final CommandCenter commandCenter;

    // private ScanResult lastScanResult; // Future feature to store last scan result

    public PhotoScanner(CommandCenter commandCenter) {
        this.commandCenter = commandCenter;

        this.lastScanResult = null;
    }

    // Scan command for the drone

    public void scanArea() {
        JSONObject scanCommand = new JSONObject();
        scanCommand.put("action", "scan");
        commandCenter.addCommand(scanCommand);
    }

    // Processes the scan response from drone
    public processScanResponse(JSONObject response) {
        int cost = response.getInt("cost");
        JSONObject extras = response.getJSONObject("extras");
        
        JSONArray biomesArray = extras.getJSONArray("biomes");
        List<String> biomes = new ArrayList<>();
        for (int i = 0; i < biomesArray.length(); i++) {
            biomes.add(biomesArray.getString(i));
        }

        JSONArray creeksArray = extras.getJSONArray("creeks");
        List<String> creeks = new ArrayList<>();
        for (int i = 0; i < creeksArray.length(); i++) {
            creeks.add(creeksArray.getString(i));
        }
        
        JSONArray sitesArray = extras.getJSONArray("sites");
        List<String> sites = new ArrayList<>();
        for (int i = 0; i < sitesArray.length(); i++) {
            sites.add(sitesArray.getString(i));
        }
        
        // Store the last scan result
        lastScanResult = new ScanResult(cost, biomes, creeks, sites); 
        return lastScanResult;
    }

    // Future feature: Retrieve the last scan result
    public ScanResult getLastScanResult() {
        return lastScanResult;
    }
}
