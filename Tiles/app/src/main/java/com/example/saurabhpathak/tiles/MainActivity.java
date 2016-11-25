package com.example.saurabhpathak.tiles;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {

    public static final String PREFS_NAME = "gameData";
    private ArrayList<Tile> tileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String savedList = settings.getString("tileList", "Saurabh Pathak");
        Utils.logMessage(savedList);

        tileList = new ArrayList<Tile>();
        JSONArray list = null;
        try {
            list = new JSONArray(savedList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.length(); i++) {
            JSONObject obj = null;
            try {
                obj = list.getJSONObject(i);
                String value = (String) obj.get("value");
                String visibleValue = (String) obj.get("visibleValue");
                String status = (String) obj.get("status");
                if (i == list.length() - 1) {
                    tileList.add(new Tile(Tile.Status.locked, null, null));
                }
                else if (status.equals("locked")) {
                    tileList.add(new Tile(Tile.Status.locked, value, visibleValue));
                } else if(status.equals("unlocked")) {
                    tileList.add(new Tile(Tile.Status.unlocked, value, visibleValue));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (tileList.size() == 0 || tileList.size() != 21) {
            tileList = new ArrayList<Tile>();
            int[] values = Utils.createIntegerArrayAndExtend(20);

            values = Utils.shuffle(values);
            for(int i = 0; i < 20; i++) {
                tileList.add(new Tile(Tile.Status.locked, String.valueOf(values[i]), "TILE"));
            }

            tileList.add(new Tile(Tile.Status.locked, null, null));
        }

        TextView tv = (TextView) findViewById(R.id.clickCount);
        TextView scoreView = (TextView) findViewById(R.id.tv_winStatus);
        scoreView.setText("");
        tv.setText("Number Of Clicks: 0");

        GridView myGrid = (GridView) findViewById(R.id.myGrid);
        myGrid.setAdapter(new ButtonAdapter(this, tileList));
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            JSONArray list = Utils.getJsonListFromArrayList(tileList);

            // We need an Editor object to make preference changes.
            // All objects are from android.context.Context
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("tileList", list.toString());
            Log.d("saved list", list.toString());

            // Commit the edits!
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}