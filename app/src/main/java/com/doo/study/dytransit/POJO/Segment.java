package com.doo.study.dytransit.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dooyoungki on 12/30/15.
 */
public class Segment {
    private String name;

    @SerializedName("num_stops")
    private int numStops;

    private String travel_mode;  //setup, cycling, walking, parking,
    private String description;
    private String color;

    @SerializedName("icon_url")
    private String iconUrl;
    private String polyline;
    private List<Stop> stops;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumStops() {
        return numStops;
    }

    public void setNumStops(int numStops) {
        this.numStops = numStops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public String getTravelMode() {
        return travel_mode;
    }

    public void setTravelMode(String travel_mode) {
        this.travel_mode = travel_mode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    @Override
    public String toString() {
        final StringBuilder formatted = new StringBuilder();
        formatted.append("\n\t-name: ").append(name)
                .append("\n\t-num of stops: ").append(numStops)
                .append("\n\t-travel mode: ").append(travel_mode)
                .append("\n\t-description: ").append(description);
        for (Stop stop : stops) {
            formatted.append("\n\t-stops: ").append(stop.toString());
        }

        return formatted.toString();
    }
}
