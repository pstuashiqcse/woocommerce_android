package com.mcc.eshopper.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by nitul on 4/11/17.
 */

public class SystemUtility {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static ProgressDialog showProgressDialog (Context context, String message){

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);

        return progressDialog;
    }
}
