package com.mcc.madokdrobyo.netWork;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.widget.Button;

public class SystemInformation {

    private Context mContext;
    public SystemInformation(Context mContext){
        this.mContext = mContext;
    }

    public boolean supportRuntimeRequest() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return true;
        else return false;
    }

    public boolean isConnected(){
        ConnectivityManager connection = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connection != null){
            NetworkInfo networkInfo = connection.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnectedOrConnecting())
                return true;
            else return false;
        }
        else return false;
    }

    public static void accessNetworkSettings(final Context context){
        AlertDialog.Builder dialogueBuilder = new AlertDialog.Builder(context);

        dialogueBuilder.setMessage("Turn on internet connection")
                .setTitle("Not Connected to Internet")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent mSettings = new Intent(Settings.ACTION_SETTINGS);
                        context.startActivity(mSettings);
                    }
                });

        dialogueBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = dialogueBuilder.create();
        dialog.show();

        Button btnPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        btnPositive.setTextColor(Color.GRAY);
        btnPositive.setTextSize(15);

        Button btnNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        btnNegative.setTextColor(Color.GRAY);
        btnNegative.setTextSize(15);
    }

}
