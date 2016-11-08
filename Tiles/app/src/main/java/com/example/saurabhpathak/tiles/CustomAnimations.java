package com.example.saurabhpathak.tiles;

import android.view.animation.RotateAnimation;
import android.widget.Button;

/**
 * Created by saurabhpathak on 08/11/2016.
 */

public class CustomAnimations {
    public static void rotateAnimation(Button btn) {
        RotateAnimation a = new RotateAnimation(0, 360, btn.getWidth()/2, btn.getHeight()/2);
        a.setFillAfter(true);
        a.setDuration(200);
        btn.startAnimation(a);
        //btn1.clearAnimation();
        //btn1.setVisibility(btn1.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
    }
}
