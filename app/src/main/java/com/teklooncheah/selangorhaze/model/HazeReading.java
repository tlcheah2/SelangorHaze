package com.teklooncheah.selangorhaze.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by tekloon on 11/14/15.
 */
public class HazeReading {

    private String pagi;
    private String last_updated;
    private String lokasi;
    private String negeri;
    private String terkini;
    private Location location;
    private String petang;
    private ArrayList<LatestReading> data;
    private LatestReading latest;
    private String tengah_hari;

    public float getTempDistance() {
        return tempDistance;
    }

    public void setTempDistance(float tempDistance) {
        this.tempDistance = tempDistance;
    }

    private float tempDistance;

    public String getPagi() {
        return pagi;
    }

    public void setPagi(String pagi) {
        this.pagi = pagi;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getTerkini() {
        return terkini;
    }

    public void setTerkini(String terkini) {
        this.terkini = terkini;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPetang() {
        return petang;
    }

    public void setPetang(String petang) {
        this.petang = petang;
    }

    public ArrayList<LatestReading> getData() {
        return data;
    }

    public void setData(ArrayList<LatestReading> data) {
        this.data = data;
    }

    public LatestReading getLatest() {
        return latest;
    }

    public void setLatest(LatestReading latest) {
        this.latest = latest;
    }

    public String getTengah_hari() {
        return tengah_hari;
    }

    public void setTengah_hari(String tengah_hari) {
        this.tengah_hari = tengah_hari;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public static HazeReading deserialize(String json){
        return new Gson().fromJson(json, HazeReading.class);
    }

    public static ArrayList<HazeReading> deserializeList(String json){
        return new Gson().fromJson(json, new TypeToken<ArrayList<HazeReading>>(){}.getType());
    }
}
