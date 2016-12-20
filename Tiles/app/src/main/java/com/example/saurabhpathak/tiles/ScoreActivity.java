package com.example.saurabhpathak.tiles;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import static com.example.saurabhpathak.tiles.MainActivity.PREFS_NAME;

public class ScoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final String scoreList = settings.getString("scores", "");
        if (scoreList.equals("")) {
            return;
        } else {
            return;
        }
    }
}
