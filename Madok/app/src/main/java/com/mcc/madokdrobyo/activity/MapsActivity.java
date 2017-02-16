package com.mcc.madokdrobyo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import com.mcc.madokdrobyo.R;
import com.mcc.madokdrobyo.constants.AppConsts;
import com.mcc.madokdrobyo.constants.MapConsts;
import com.mcc.madokdrobyo.maps.GetDataFromUrl;
import com.mcc.madokdrobyo.maps.GetDirection;
import com.mcc.madokdrobyo.maps.MapUtils;
import com.mcc.madokdrobyo.utils.Permissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements com.mcc.madokdrobyo.maps.Listener,
                                                              LocationListener,
                                                              OnMapReadyCallback,
                                                              GoogleApiClient.ConnectionCallbacks,
                                                              GoogleApiClient.OnConnectionFailedListener
{
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private LatLng mSearchedLatLng;
    private Button btnCurrentLocation;
    private Button btnGetDirettion;
    private TextView tvAddress;
    private MapUtils mapUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tvAddress = (TextView) findViewById(R.id.tvSeacchedLocation);
        btnGetDirettion = (Button) findViewById(R.id.btnGetDirection);
        btnCurrentLocation = (Button) findViewById(R.id.btnCurrentLocation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setOnClickListener(toolbar);
    }

    private void setOnClickListener(Toolbar toolbar) {
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapUtils.onMapSearch();

            }
        });

        btnCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCache();
                mapUtils.getLocation(mapUtils.getLatLng(mCurrentLocation));
            }
        });

        btnGetDirettion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRoute();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mMap = googleMap;
        mapUtils = new MapUtils(this, mMap);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                toolbar.hideOverflowMenu();
            }
        });

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        getUpdatedLocation();
    }

    private void getUpdatedLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED ) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mapUtils.getLocation(mapUtils.getLatLng(mCurrentLocation));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MapConsts.PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK){

            final Place place = PlacePicker.getPlace(this, data);
            setResources(place);
            mapUtils.getLocation(place.getLatLng());
        }
    }

    private void clearCache(){
        mMap.clear();
        tvAddress.setText(" ");
        btnGetDirettion.setVisibility(View.GONE);
    }

    private void setResources(Place place) {

        mSearchedLatLng = place.getLatLng();
        String result = place.getName() + ", " + place.getAddress();
        tvAddress.setText(result);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(mSearchedLatLng);
        mMap.addMarker(markerOptions);

        btnGetDirettion.setBackgroundResource(R.drawable.get_direction);
        btnGetDirettion.setVisibility(View.VISIBLE);
    }

    private void showRoute(){
        mapUtils.buildBound(mCurrentLocation, mSearchedLatLng);

        String url = GetDataFromUrl.getDirectionsUrl( mapUtils.getLatLng(mCurrentLocation), mSearchedLatLng);
        GetDirection getDirections = new GetDirection(MapsActivity.this);
        getDirections.startGettingDirections(url);
    }

    @Override
    public void onSuccessfullRouteFetch(final List<List<HashMap<String, String>>> result) {
        new Thread(new Runnable() {
            PolylineOptions lineOptions;

            @Override
            public void run() {
                ArrayList<LatLng> points;

                // Traversing through all the routes
                for (List<HashMap<String, String>> path : result) {
                    points = new ArrayList<LatLng>();
                    lineOptions = new PolylineOptions();

                    // Get all the points for this route
                    for (HashMap<String, String> point : path) {

                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);
                        points.add(position);
                    }

                    // Adding all the points in the route to LineOptions
                    lineOptions.addAll(points);
                    lineOptions.width(10);
                    lineOptions.color(R.color.colorPrimaryDark);
                }

                //Do all UI operations on the UI thread only...
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Drawing polyline in the Google Map for the this route
                        mMap.addPolyline(lineOptions);
                    }
                });

            }
        }).start();

    }

    @Override
    public void onFail() {
        Log.i(MapConsts.TAG, "Failed to get directions from Google...");
    }

}
