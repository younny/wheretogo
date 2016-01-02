package com.doo.study.dytransit.view;

/**
 * Created by dooyoungki on 1/2/16.
 */
public class PlaceAutoComplete {
    private CharSequence primaryName;
    private CharSequence fullName;
    private String placeId;

    public PlaceAutoComplete(CharSequence primaryName, CharSequence fullName, String placeId) {
        this.primaryName = primaryName;
        this.fullName = fullName;
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "name="+primaryName.toString()+", id="+placeId;
    }


    public CharSequence getFullName() {
        return fullName;
    }

    public void setFullName(CharSequence fullName) {
        this.fullName = fullName;
    }

    public CharSequence getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(CharSequence primaryName) {
        this.primaryName = primaryName;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
