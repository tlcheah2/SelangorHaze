package com.teklooncheah.selangorhaze.model;

/**
 * Created by tekloon on 11/14/15.
 */
public class Location {

    private String area;
    private String state;

    private Coordinates coordinates;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
