package com.doo.study.dytransit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;


/**
 * Created by dooyoungki on 1/3/16.
 */
public class BaseActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = BaseActivity.class.getSimpleName();

    protected GoogleApiClient googleClient;


    protected void ConnectGoogleService() {
        googleClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleClient != null)
            googleClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleClient != null)
            googleClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "Google Places API connection failed with error code: " + connectionResult.getErrorCode());

    }

    public GoogleApiClient getGoogleClient() {
        return googleClient;
    }

    public void setGoogleClient(GoogleApiClient googleClient) {
        this.googleClient = googleClient;
    }

}
