package com.doo.study.dytransit.fragment;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by dooyoungki on 1/3/16.
 */
public class BaseMapFragment extends SupportMapFragment implements OnMapReadyCallback{
    protected static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    protected boolean mPermissionDenied = false;
    protected GoogleApiClient googleClient;


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    public void setGoogleClient(GoogleApiClient googleClient) {
        this.googleClient = googleClient;
    }

    public GoogleApiClient getGoogleClient() {
        return googleClient;
    }
}
