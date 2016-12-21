package com.example.saurabhpathak.tiles;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    int[] arrayOfInts, arrayOfInts2;
    ArrayList<Tile> tileList;
    JSONArray jsonList;

    @Before
    public void setup() throws JSONException {
        arrayOfInts = new int[]{1, 2, 3, 1, 2, 3};
        arrayOfInts2 = Utils.createIntegerArrayAndExtend(6);

        tileList = new ArrayList<Tile>();
        tileList.add(new Tile(Tile.Status.locked, "tile1", "tile1"));
        tileList.add(new Tile(Tile.Status.locked, "tile2", "tile2"));

//        jsonList = new JSONArray();
//        JSONObject obj;
//        for (int i = 0; i < tileList.size(); i++) {
//            obj = new JSONObject();
//            obj.put("value", tileList.get(i).getValue() == null ? "" : tileList.get(i).getValue());
//            obj.put("visibleValue", tileList.get(i).getVisibleValue() == null ? "" : tileList.get(i).getVisibleValue());
//            obj.put("status", tileList.get(i).getStatus());
//            jsonList.put(obj);
//        }
    }
    @Test
    public void isArrayMatching() throws Exception {
        assertArrayEquals(arrayOfInts, arrayOfInts2);
    }
    @Test
    public void isListUnlocked() {
        assertNotEquals(Utils.isListUnlocked(tileList), true);
    }
    @Test
    public void isListLocked() {
        assertEquals(Utils.isListLocked(tileList), true);
    }
    @Test
    public void isJsonValid() throws JSONException {
        assertEquals(Utils.getJsonListFromArrayList(tileList), jsonList);
    }
}