package com.doo.study.dytransit.POJO;

import java.util.List;

/**
 * Created by dooyoungki on 12/30/15.
 */
public class RouteSet {

    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        final StringBuilder formatted = new StringBuilder();
        formatted.append("Routes: ");
        for(final Route route : routes){
            formatted.append(route.toString());
        }
        return formatted.toString();
    }
}
