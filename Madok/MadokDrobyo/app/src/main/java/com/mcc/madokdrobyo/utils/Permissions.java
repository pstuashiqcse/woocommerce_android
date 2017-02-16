package com.mcc.madokdrobyo.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.mcc.madokdrobyo.constants.AppConsts;

/**
 * Created by nitul on 1/26/17.
 */

public class Permissions {

//    public boolean isNetworkPermissionGranted(Context context){
//
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) ==
//                PackageManager.PERMISSION_GRANTED) return true;
//        else return false;
//    }

    public boolean isLocationAccessAllowed( Context context){

        if (fineLocation(context) == PackageManager.PERMISSION_GRANTED) return true;
        else return false;
    }

    private int fineLocation(Context context){
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
    }

//    private int coraseLocation(Context context){
//        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
//    }


    public void generatePermissionRequest(Activity activity) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestForLocation(activity);
        }
        else requestForLocation(activity);
    }

    private void requestForLocation(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, AppConsts.REQUEST_CODE);
    }

}
