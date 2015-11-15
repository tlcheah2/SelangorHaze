package com.teklooncheah.selangorhaze.model;

import com.google.gson.Gson;

/**
 * Created by tekloon on 11/14/15.
 */
public class LatestReading {

    private String index;
    private String time;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
