package ca.mcmaster.se2aa4.island.team09;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class PhotoScanner {
    private final CommandCenter commandCenter;
    private ScanResult lastScanResult;
    private ScanManager scanManager;

    public PhotoScanner(CommandCenter commandCenter) {
        this.commandCenter = commandCenter;
        this.lastScanResult = null;
        this.scanManager = new ScanManager();
    }

    public void scanArea() {
        JSONObject scanCommand = new JSONObject();
        scanCommand.put("action", "scan");
        commandCenter.addCommand(scanCommand);
    }

    public ScanResult processScanResponse(JSONObject response, DroneState drone) {
        int cost = response.getInt("cost");
        JSONObject extras = response.getJSONObject("extras");

        List<String> biomes = jsonArrayToList(extras.getJSONArray("biomes"));
        List<String> creeks = jsonArrayToList(extras.getJSONArray("creeks"));
        List<String> sites = jsonArrayToList(extras.getJSONArray("sites"));

        lastScanResult = new ScanResult(cost, biomes, creeks, sites);
        scanManager.storeScanResult(lastScanResult);
        drone.consumeBattery(cost);
        return lastScanResult;
    }

    public ScanResult getLastScanResult() {
        return lastScanResult;
    }

    public ScanManager getScanManager() {
        return scanManager;
    }

    private List<String> jsonArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }
}