package com.mcc.madokdrobyo.constants;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by nitul on 2/12/17.
 */

public class MapConsts {

    public static final int PLACE_PICKER_REQUEST = 1;
    public static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));

    public static final String REHAB_INFORMATION = "Rehab Info";
    public static final String TAG = "MAP DEMO";

    public static String BASE_URL_MAPS_DIRECTION = "https://maps.googleapis.com/maps/api/directions/";
    public static String MAPS_DATA_TYPE = "json";
}
