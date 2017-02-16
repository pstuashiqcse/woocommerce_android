package com.mcc.madokdrobyo.maps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mcc.madokdrobyo.activity.MapsActivity;
import com.mcc.madokdrobyo.constants.AppConsts;
import com.mcc.madokdrobyo.constants.MapConsts;

public class MapUtils {
    private Activity activity;
    private GoogleMap mMap;

    public MapUtils(Activity activity, GoogleMap mMap){
        this.activity = activity;
        this.mMap = mMap;
    }

    public LatLng getLatLng(Location location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    public void onMapSearch(){

        try {
            mMap.clear();
            PlacePicker.IntentBuilder intentBuilder =
                    new PlacePicker.IntentBuilder();
            intentBuilder.setLatLngBounds(MapConsts.BOUNDS_MOUNTAIN_VIEW);
            Intent intent = intentBuilder.build(activity);
            activity.startActivityForResult(intent, MapConsts.PLACE_PICKER_REQUEST);
        }
        catch (GooglePlayServicesRepairableException
                | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }


    public void getLocation(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);

        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
    }

    public void buildBound(Location mCurrentLocation, LatLng mSearchedLatLng){

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        boundsBuilder.include(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()));
        boundsBuilder.include(mSearchedLatLng);

        LatLngBounds bounds = boundsBuilder.build();
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 3));
    }
}
