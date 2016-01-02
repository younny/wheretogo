package com.doo.study.dytransit.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.doo.study.dytransit.R;
import com.doo.study.dytransit.view.PlaceAutoComplete;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by dooyoungki on 12/31/15.
 */
public class PlaceAutocompleteAdapter extends ArrayAdapter<PlaceAutoComplete> implements Filterable {
    private static final String TAG = PlaceAutocompleteAdapter.class.getSimpleName();
    private Context context;
    private int resourceId;
    //    private List<Stop> stops;
//    private ArrayList<Stop> temp, suggestions;
    private GoogleApiClient googleApiClient;
    private ArrayList<PlaceAutoComplete> resultList;


    public PlaceAutocompleteAdapter(Context context, int resourceId) {
        super(context, resourceId);
        this.context = context;
        this.resourceId = resourceId;
    }

    public void setGoogleApiClient(GoogleApiClient client) {
        googleApiClient = client;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public PlaceAutoComplete getItem(int position) {
        return resultList.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resourceId, parent, false);
        }
        TextView nameView = (TextView) convertView.findViewById(R.id.name);
        TextView detailsView = (TextView) convertView.findViewById(R.id.detail);
        PlaceAutoComplete item = getItem(position);

        nameView.setText(item.getPrimaryName());
        detailsView.setText(item.getFullName());
//            if(stop != null && stop.getName() != null){
//                nameView.setText(stop.getName());
//                detailsView.setText(stop.getLat() +", "+ stop.getLng());
//            }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return placeFilter;
    }

    private ArrayList<PlaceAutoComplete> getPredictions(CharSequence constraint) {
        if (googleApiClient != null) {
            Log.d(TAG, "Executing autocomplete query for: " + constraint);
            LatLngBounds bounds = new LatLngBounds( new LatLng( 39.906374, -105.122337 ), new LatLng( 39.949552, -105.068779 ) );
            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_GEOCODE)
                    .build();
            PendingResult<AutocompletePredictionBuffer> results = Places.GeoDataApi.
                    getAutocompletePredictions(googleApiClient, constraint.toString(), bounds, typeFilter);
            AutocompletePredictionBuffer autocompletePredictions = results.await(60, TimeUnit.SECONDS);
            final Status status = autocompletePredictions.getStatus();
            if (!status.isSuccess()) {
                Log.e(TAG, "Error getting place predictions: " + status.toString());
                autocompletePredictions.release();
                return null;
            }
            Log.d(TAG, "Query completed. Received " + autocompletePredictions.getCount() + " predictions.");
            Iterator<AutocompletePrediction> iterator = autocompletePredictions.iterator();
            ArrayList resultList = new ArrayList<>(autocompletePredictions.getCount());
            while (iterator.hasNext()) {
                AutocompletePrediction prediction = iterator.next();
                resultList.add(new PlaceAutoComplete(prediction.getPrimaryText(null),
                        prediction.getFullText(null), prediction.getPlaceId()));
            }
            // Buffer release
            autocompletePredictions.release();
            return resultList;
        }
        Log.e(TAG, "Google API client is not connected.");
        return null;


    }

    private Filter placeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null) {
                // Query the autocomplete API for the entered constraint
                resultList = getPredictions(constraint);
                if (resultList != null) {
                    // Results
                    results.values = resultList;
                    results.count = resultList.size();
                }
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results != null && results.count > 0) {
                // The API returned at least one result, update the data.
                notifyDataSetChanged();
            } else {
                // The API did not return any results, invalidate the data set.
                notifyDataSetInvalidated();
            }
        }
    };

}
