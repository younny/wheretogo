package com.doo.study.dytransit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.ExpandableListView;

import com.doo.study.dytransit.POJO.Route;
import com.doo.study.dytransit.POJO.Segment;
import com.doo.study.dytransit.POJO.Stop;
import com.doo.study.dytransit.fragment.HomeMapFragment;
import com.doo.study.dytransit.fragment.RouteMapFragment;
import com.doo.study.dytransit.view.adapter.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dooyoungki on 1/3/16.
 */
public class RouteDetailsActivity extends BaseActivity {
    private static final String TAG = RouteDetailsActivity.class.getSimpleName();

    private RouteMapFragment routeMapFragment;

    private Route selectedRoute;

    private ExpandableListView segmentsList;
    private List<String> headerList = new ArrayList<>();
    private HashMap<String, List<Stop>> childDataMap = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);

        segmentsList = (ExpandableListView) findViewById(R.id.segment_list);
        selectedRoute = (Route) getIntent().getSerializableExtra("selected_route");
        Log.e(TAG,"selected route : " + selectedRoute.toString());

        setSegmentsData();
        showMap();
    }

    private void setSegmentsData() {
        for(Segment seg : selectedRoute.getSegments()){
            String key;
            if(seg.getName() == null){
                key = seg.getTravelMode();
            }else{
                key = seg.getName()+", "+seg.getTravelMode();
            }
            headerList.add(key);
            childDataMap.put(key, seg.getStops());
        }
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, headerList, childDataMap);
        segmentsList.setAdapter(adapter);
    }

    private void showMap() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        routeMapFragment = (RouteMapFragment) fragmentManager.findFragmentByTag(HomeMapFragment.TAG);
        if (routeMapFragment == null) {
            routeMapFragment = RouteMapFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.map_container, routeMapFragment, HomeMapFragment.TAG)
                    .commit();
        }
        routeMapFragment.setGoogleClient(getGoogleClient());
    }

}
