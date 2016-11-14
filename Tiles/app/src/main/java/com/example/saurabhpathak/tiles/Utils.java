package com.example.saurabhpathak.tiles;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by saurabhpathak on 08/11/2016.
 */

public class Utils {
    public static int[] shuffle(int[] array) {
        int currentIndex = array.length,
                temporaryValue, randomIndex;
        while (0 != currentIndex) {
            randomIndex = (int)Math.floor(Math.random() * currentIndex);
            currentIndex -= 1;
            temporaryValue = array[currentIndex];
            array[currentIndex] = array[randomIndex];
            array[randomIndex] = temporaryValue;
        }
        return array;
    }
    public static int[] createIntegerArrayAndExtend (int numberOfElements) {
        int[] values = new int[numberOfElements];
        for (int i = 1; i <= numberOfElements/2; i++) {
            values[i-1] = i;
            values[i+9] = i;
        }
        return values;
    }
    public static void showLongToast(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG);
    }
    public static boolean isListUnlocked(ArrayList<Tile> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).getStatus() != Tile.Status.unlocked) {
                return false;
            }
        }
        return true;
    }
}
