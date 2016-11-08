package com.example.saurabhpathak.tiles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> tileList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            tileList.add(String.valueOf(i));
        }

        final TextView tv = (TextView) findViewById(R.id.mainActivityTextView);
        tv.setText(R.string.mainActivityText);

        final Button btn1 = (Button) findViewById(R.id.button1);

        GridView myGrid = (GridView) findViewById(R.id.myGrid);
        myGrid.setAdapter(new ButtonAdapter(this, tileList));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAnimations.rotateAnimation(btn1);
                if (tv.getText().toString().indexOf("Pathak") >= 0){
                    tv.setText(R.string.mainActivitytext2);
                } else {
                    tv.setText(R.string.mainActivityText);
                }
            }
        });
    }
}

class CustomAnimations {
    public static void rotateAnimation(Button btn) {
        RotateAnimation a = new RotateAnimation(0, 360, btn.getWidth()/2, btn.getHeight()/2);
        a.setFillAfter(true);
        a.setDuration(200);
        btn.startAnimation(a);
        //btn1.clearAnimation();
        //btn1.setVisibility(btn1.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
    }
}
