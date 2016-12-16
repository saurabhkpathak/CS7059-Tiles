package com.example.saurabhpathak.tiles;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            values[i+(numberOfElements/2 - 1)] = i;
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
    public static boolean isListLocked(ArrayList<Tile> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).getStatus() != Tile.Status.locked) {
                return false;
            }
        }
        return true;
    }
    public static JSONArray getJsonListFromArrayList(ArrayList<Tile> tileList) throws JSONException {
        JSONArray list = new JSONArray();
        JSONObject obj;
        for (int i = 0; i < tileList.size(); i++) {
            obj = new JSONObject();
            obj.put("value", tileList.get(i).getValue() == null ? "" : tileList.get(i).getValue());
            obj.put("visibleValue", tileList.get(i).getVisibleValue() == null ? "" : tileList.get(i).getVisibleValue());
            obj.put("status", tileList.get(i).getStatus());
            list.put(obj);
        }
        return list;
    }
    public static ArrayList<Tile> getTileListFromStringifiedList(String l) throws JSONException {
        JSONArray list = new JSONArray(l);
        ArrayList<Tile> tileList = new ArrayList<Tile>();
        for (int i = 0; i < list.length(); i++) {
            JSONObject obj = null;
            try {
                obj = list.getJSONObject(i);
                String value = (String) obj.get("value");
                String visibleValue = (String) obj.get("visibleValue");
                String status = (String) obj.get("status");
                if (i == list.length() - 1) {
                    tileList.add(new Tile(Tile.Status.locked, null, null));
                } else if (status.equals("locked")) {
                    tileList.add(new Tile(Tile.Status.locked, value, visibleValue));
                } else if (status.equals("unlocked")) {
                    tileList.add(new Tile(Tile.Status.unlocked, value, visibleValue));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return tileList;
    }
    public static void logMessage(String msg) {
        Log.d("ttttttt", msg);
    }
}
