package com.example.saurabhpathak.tiles;

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
}
