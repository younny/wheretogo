package com.doo.study.dytransit.POJO;

/**
 * Created by dooyoungki on 12/31/15.
 */
public class Stop {

    private double lat;
    private double lng;
    private String datetime;
    private String name;
    private Object properties;

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object properties) {
        this.properties = properties;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[name="+name+", lat="+lat+", lng="+lng
                +", datetime="+datetime+", properties="+properties+"]";
    }
}

