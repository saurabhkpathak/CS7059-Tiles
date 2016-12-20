package com.example.saurabhpathak.tiles;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.saurabhpathak.tiles.MainActivity.PREFS_NAME;

public class IndexActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        Button btn = (Button)findViewById(R.id.navigateToGame);
        Button resumeBtn = (Button)findViewById(R.id.resumeGame);
        btn.setText("Start Game");

        Button scoreBtn = (Button)findViewById(R.id.scoreBtn);
        scoreBtn.setText("Scores");
        scoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, ScoreActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final String savedList = settings.getString("tileList", "");

        if (!savedList.equals("")) {
            resumeBtn.setText("Resume Game");
            resumeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(IndexActivity.this, MainActivity.class);
                    intent.putExtra("isStartOrResume", "resume");
                    startActivity(intent);
                }
            });
        } else {
            resumeBtn.setVisibility(View.GONE);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, MainActivity.class);
                intent.putExtra("isStartOrResume", "start");
                startActivity(intent);
            }
        });

    }

}
