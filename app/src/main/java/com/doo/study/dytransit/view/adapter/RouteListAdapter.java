package com.doo.study.dytransit.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.doo.study.dytransit.POJO.Route;
import com.doo.study.dytransit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dooyoungki on 1/2/16.
 */
public class RouteListAdapter extends ArrayAdapter<Route> {
    private List<Route> routes = new ArrayList<>();
    private Context context;
    private int resource;

    public RouteListAdapter(Context context, int resource, List<Route> routes) {
        super(context, resource, routes);
        this.context = context;
        this.routes = routes;
        this.resource = resource;
    }

    @Override
    public Route getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
            holder.segments = (TextView) convertView.findViewById(R.id.segments);
            holder.routeType = (TextView) convertView.findViewById(R.id.route_type);
            holder.routePrice = (TextView) convertView.findViewById(R.id.route_price);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Route route = routes.get(position);
        holder.routeType.setText(route.getType());
        holder.routePrice.setText(route.getPrice().toString());
        holder.segments.setText(route.getSegmentsString());

        return convertView;
    }

    private class ViewHolder {
        public TextView routeType;
        public TextView routePrice;
        public TextView segments;

    }
}

