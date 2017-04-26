package com.mcc.eshopper.utility;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by nitul on 4/11/17.
 */

public class AppUtility {

    public static void showToast (Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
