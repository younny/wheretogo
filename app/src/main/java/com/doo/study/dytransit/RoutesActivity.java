package com.doo.study.dytransit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.doo.study.dytransit.POJO.Route;
import com.doo.study.dytransit.view.adapter.RouteListAdapter;

/**
 * Created by dooyoungki on 1/2/16.
 */
public class RoutesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = RoutesActivity.class.getSimpleName();
    private ListView routeList;
    private RouteListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        routeList = (ListView) findViewById(R.id.route_list);
        routeList.setOnItemClickListener(this);

        adapter = new RouteListAdapter(this, R.layout.routes_list_row, MainActivity.getRouteSet().getRoutes());
        routeList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Route selectedRoute = (Route) parent.getAdapter().getItem(position);
        //TODO
        startActivity(new Intent(this, RouteDetailsActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                .putExtra("selected_route", selectedRoute));
    }
}
