package com.example.saurabhpathak.tiles;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

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
            JSONArray scores = null;
            try {
                scores = new JSONArray(scoreList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < scores.length(); i++) {
                try {
                    int id = R.id.class.getField("score" + (i+1)).getInt(0);
                    Log.d("yyyyyyyy", String.valueOf(id));
                    TextView scoreView = (TextView)findViewById(id);
                    scoreView.setText(String.valueOf(i+1) + ". " + String.valueOf(scores.getJSONObject(i).get("value")));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
