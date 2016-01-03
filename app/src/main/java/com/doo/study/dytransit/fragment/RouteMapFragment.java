package com.doo.study.dytransit.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by dooyoungki on 1/3/16.
 */
public class RouteMapFragment extends BaseMapFragment{
    public static final String TAG = RouteMapFragment.class.getSimpleName();

    private GoogleMap gMap;

    public static RouteMapFragment newInstance() {
        return new RouteMapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,"map create view");
        View v = super.onCreateView(inflater, container, savedInstanceState);
        getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(TAG,"map ready");
        super.onMapReady(googleMap);
        gMap = googleMap;

    }

}
