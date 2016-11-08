package com.example.saurabhpathak.tiles;

/**
 * Created by saurabhpathak on 08/11/2016.
 */

public class Tile {

    String value, visibleValue;
    public enum Status {locked, visible, unlocked};
    Status status;

    public String getValue() {
        return this.value;
    }
    public String getVisibleValueValue() {
        return this.visibleValue;
    }
    public Status getStatus() {
        return this.status;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setVisibleValue(String visibleValue) {
        this.visibleValue = visibleValue;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    Tile(Status status, String value, String visibleValue) {
        this.status = status;
        this.value = value;
        this.visibleValue = visibleValue;
    }
}
