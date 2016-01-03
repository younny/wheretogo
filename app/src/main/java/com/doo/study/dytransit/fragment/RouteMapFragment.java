package com.doo.study.dytransit.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doo.study.dytransit.POJO.Route;
import com.doo.study.dytransit.POJO.Segment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dooyoungki on 1/3/16.
 */
public class RouteMapFragment extends BaseMapFragment {
    public static final String TAG = RouteMapFragment.class.getSimpleName();

    private GoogleMap gMap;
    private Route route;
    private List<Segment> segments;

    public static RouteMapFragment newInstance() {
        return new RouteMapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "map create view");
        View v = super.onCreateView(inflater, container, savedInstanceState);
        getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(TAG, "map ready");
        super.onMapReady(googleMap);
        gMap = googleMap;
        showMyRoute();
    }

    private void showMyRoute() {
        List<LatLng> latLngList = new ArrayList<>();
        LatLng startPoint;
        LatLng endPoint;
        PolylineOptions options = new PolylineOptions();
        options.color(Color.RED);
        options.width(8);
        for(Segment seg : segments){
            if (seg.getPolyline() != null) {
                latLngList = PolyUtil.decode(seg.getPolyline());
                options.addAll(latLngList);
            }
        }
        startPoint = latLngList.get(0);
        endPoint = latLngList.get(latLngList.size() -1);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 14));
        gMap.addMarker(new MarkerOptions()
                .position(startPoint)
                .title("start here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        Marker endMarker = gMap.addMarker(new MarkerOptions()
                .position(endPoint)
                .title("arrive here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        endMarker.showInfoWindow();

        gMap.addPolyline(options);

    }

    public void setRoute(Route route) {
        this.route = route;
        segments = route.getSegments();
    }
}
