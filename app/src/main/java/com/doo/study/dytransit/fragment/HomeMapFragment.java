package com.doo.study.dytransit.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

import com.doo.study.dytransit.MainActivity;
import com.doo.study.dytransit.RoutesActivity;
import com.doo.study.dytransit.model.User;
import com.doo.study.dytransit.utils.PermissionUtils;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



/**
 * Created by dooyoungki on 12/30/15.
 */
public class HomeMapFragment extends BaseMapFragment implements GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnInfoWindowClickListener{
    public static final String TAG = HomeMapFragment.class.getSimpleName();

    private GoogleMap mMap;
    private Marker myMarker;
    private Marker placeMarker;

    public static HomeMapFragment newInstance() {
        return new HomeMapFragment();
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

        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        enableMyLocation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"map destroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"map detach");
    }

    public void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            PermissionUtils.requestPermission(((AppCompatActivity) getContext()), LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
            Log.e(TAG," request permission");
        } else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
            findMyLocation();
        }
    }

    public void findMyLocation() {
        Log.e(TAG,"FInd my location");
        final User user = MainActivity.getUser();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(googleClient, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    Log.d(TAG, String.format("Place '%s' with " + "likelihood: %g", placeLikelihood.getPlace().getName(), placeLikelihood.getLikelihood()));
                }
                user.setCurrentPlace(likelyPlaces.get(0).getPlace());
                Log.e(TAG,"user's current location > " + user.getCurrentPlace().getName() + "/place id : "+ user.getCurrentPlace().getId());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(user.getCurrentPlace().getLatLng(), 13));
                myMarker = mMap.addMarker(new MarkerOptions()
                        .position(user.getCurrentPlace().getLatLng())
                        .title("My Location")
                        .snippet(user.getCurrentPlace().getName().toString())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                myMarker.showInfoWindow();
                likelyPlaces.release();
            }
        });
    }

    public void findLocation(final Place place){
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 12));
        if(placeMarker != null){
            placeMarker.remove();
        }
        placeMarker = mMap.addMarker(new MarkerOptions()
                .position(place.getLatLng())
                .title(place.getName().toString())
                .snippet("Click to Find Route!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
        placeMarker.showInfoWindow();

        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final long duration = 1500;

        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = Math.max(1 - interpolator.getInterpolation((float) elapsed / duration), 0);
                placeMarker.setAnchor(0.5f, 1.0f + 2*t);
                if(t > 0.0){
                    handler.postDelayed(this, 16);
                }
            }
        });
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        startActivity(new Intent(getActivity(), RoutesActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }



    public boolean isPermissionDenied() {
        return mPermissionDenied;
    }

    public void setPermissionDenied(boolean mPermissionDenied) {
        this.mPermissionDenied = mPermissionDenied;
    }

}
