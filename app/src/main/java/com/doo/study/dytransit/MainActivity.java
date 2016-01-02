package com.doo.study.dytransit;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.doo.study.dytransit.POJO.RouteSet;
import com.doo.study.dytransit.fragment.MapFragment;
import com.doo.study.dytransit.model.User;
import com.doo.study.dytransit.utils.PermissionUtils;
import com.doo.study.dytransit.view.MySearchView;
import com.doo.study.dytransit.view.PlaceAutoComplete;
import com.doo.study.dytransit.view.adapter.PlaceAutocompleteAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
//    private final int REQUEST_CODE_ASK_PERMISSION = 1421;

    private GoogleApiClient googleClient;
    private ProgressDialog progressDialog;

    private MapFragment mapFragment;
    protected MySearchView searchView;

    private static RouteSet routeSet;
    private static User user;

    public static User getUser() {
        return user;
    }

    public static RouteSet getRouteSet() {
        return routeSet;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "on New Intent");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "on Create");
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        //set floating action button
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_ASK_PERMISSION);


        user = new User();
        ConnectGoogleService();
        ShowMap();
        LoadMapData();
//        QueryRoutes();
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ConnectGoogleService() {
        googleClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    private void LoadMapData() {
        try {
            InputStream is = getAssets().open("data.json");
            Reader reader = new InputStreamReader(is);
            routeSet = new Gson().fromJson(reader, RouteSet.class);
            reader.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void QueryRoutes() {
//        for (Route route : routeSet.getRoutes()) {
//            Log.d(TAG, "route type: " + route.getType() + "/price: " + route.getPrice().toString());
//            for(Segment seg : route.getSegments()){
//                Log.d(TAG,"segment : " + seg.getTravelMode());
//            }
//
//        }
        startActivity(new Intent(this, RoutesActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

//
//        Intent routesIntent = new Intent(this, RoutesActivity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(RoutesActivity.class);
//        stackBuilder.addNextIntent(routesIntent);
//        PendingIntent routesPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setContentIntent(routesPendingIntent).build();

    }

    private void ShowMap() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentByTag(MapFragment.TAG);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_map_container, mapFragment, MapFragment.TAG)
                    .commit();
        }
        mapFragment.setGoogleClient(googleClient);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.options_menu, menu);
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (MySearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final PlaceAutoComplete item = (PlaceAutoComplete) parent.getAdapter().getItem(position);
                final String placeId = item.getPlaceId();
                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(googleClient, placeId);
                placeResult.setResultCallback(updatePlaceDetailsCallback);
                TextView nameView = (TextView) view.findViewById(R.id.name);
                searchView.setText(nameView.getText().toString());
            }
        });
        PlaceAutocompleteAdapter adapter = new PlaceAutocompleteAdapter(this, R.layout.auto_view_row);
        adapter.setGoogleApiClient(googleClient);
        searchView.setAdapter(adapter);


        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (PermissionUtils.isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
            mapFragment.enableMyLocation();
            Log.e(TAG,"request is granted");
        } else {
            mapFragment.setPermissionDenied(true);
        }
    }

    private ResultCallback<PlaceBuffer> updatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e(TAG, "Place query did not complete, error : " + places.getStatus().toString());
                return;
            }
            final Place place = places.get(0);
            Log.i(TAG, Html.fromHtml(place.getName().toString())
                    + "\n" + Html.fromHtml(place.getId())
                    + "\n" + Html.fromHtml(String.valueOf(place.getLatLng())
                    + "\n" + Html.fromHtml(place.getAddress().toString())));
            mapFragment.findLocation(place);
            searchView.clearFocus();
            places.release();
        }
    };

    private void ShowProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.searching_routes));
        progressDialog.setCancelable(false);
        progressDialog.show();
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


}
