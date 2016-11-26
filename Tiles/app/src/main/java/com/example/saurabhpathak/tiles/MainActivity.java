package com.example.saurabhpathak.tiles;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends Activity {

    public static final String PREFS_NAME = "gameData";
    private ArrayList<Tile> tileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String isStartOrResume = getIntent().getStringExtra("isStartOrResume");
        if (isStartOrResume.equals("start")) {
            tileList = new ArrayList<Tile>();
            int[] values = Utils.createIntegerArrayAndExtend(20);

            values = Utils.shuffle(values);
            for(int i = 0; i < 20; i++) {
                tileList.add(new Tile(Tile.Status.locked, String.valueOf(values[i]), "TILE"));
            }

            tileList.add(new Tile(Tile.Status.locked, null, null));
        } else if (isStartOrResume.equals("resume")) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            String savedList = settings.getString("tileList", "");

            if (!savedList.equals("")) {
                try {
                    tileList = new ArrayList<Tile>(Utils.getTileListFromStringifiedList(savedList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
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
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        if (Utils.isListUnlocked(tileList) || Utils.isListLocked(tileList)) {
            editor.remove("tileList");
            editor.commit();
            return;
        }
        try {
            JSONArray list = Utils.getJsonListFromArrayList(tileList);
            editor.putString("tileList", list.toString());
            Utils.logMessage(list.toString());
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}