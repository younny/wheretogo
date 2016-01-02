package com.doo.study.dytransit.model;

import com.doo.study.dytransit.POJO.Route;
import com.google.android.gms.location.places.Place;

import java.util.List;

/**
 * Created by dooyoungki on 1/1/16.
 */
public class User {

    private Route route;

    private Place currentPlace;
    private Place lastPlace;

    private Place from;
    private Place to;

    private List<Place> favoritePlaces;
    private List<Place> recentPlaces;
    private List<Route> recentRoutes;

    public User() {
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Place getCurrentPlace() {
        return currentPlace;
    }

    public void setCurrentPlace(Place currentPlace) {
        this.currentPlace = currentPlace;
    }

    public Place getLastPlace() {
        return lastPlace;
    }

    public void setLastPlace(Place lastPlace) {
        this.lastPlace = lastPlace;
    }

    public Place getFrom() {
        return from;
    }

    public void setFrom(Place from) {
        this.from = from;
    }

    public Place getTo() {
        return to;
    }

    public void setTo(Place to) {
        this.to = to;
    }

    public List<Place> getFavoritePlaces() {
        return favoritePlaces;
    }

    public void setFavoritePlaces(List<Place> favoritePlaces) {
        this.favoritePlaces = favoritePlaces;
    }

    public List<Place> getRecentPlaces() {
        return recentPlaces;
    }

    public void setRecentPlaces(List<Place> recentPlaces) {
        this.recentPlaces = recentPlaces;
    }

    public List<Route> getRecentRoutes() {
        return recentRoutes;
    }

    public void setRecentRoutes(List<Route> recentRoutes) {
        this.recentRoutes = recentRoutes;
    }
}
