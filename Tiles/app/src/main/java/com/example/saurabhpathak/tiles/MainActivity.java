package com.example.saurabhpathak.tiles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Tile> tileList = new ArrayList<Tile>();

        int[] values = Utils.createIntegerArrayAndExtend(20);

        values = Utils.shuffle(values);
        for(int i = 0; i < 20; i++) {
            tileList.add(new Tile(Tile.Status.locked, String.valueOf(values[i]), "TILE"));
        }

        tileList.add(new Tile(Tile.Status.locked, null, null));

        TextView tv = (TextView) findViewById(R.id.clickCount);
        tv.setText("Number Of Clicks: 0");

        GridView myGrid = (GridView) findViewById(R.id.myGrid);
        myGrid.setAdapter(new ButtonAdapter(this, tileList));
    }
}