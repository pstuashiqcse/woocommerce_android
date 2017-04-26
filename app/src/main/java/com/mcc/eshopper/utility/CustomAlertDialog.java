package com.mcc.eshopper.utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import java.security.PublicKey;

/**
 * Created by nitul on 4/5/17.
 */

public class CustomAlertDialog {
    private Context mContext;

    public CustomAlertDialog(Context mContext){
        this.mContext = mContext;
    }

    public Dialog createDialog( String message, String positiveTitle, String negativeTitle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton (positiveTitle, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        mContext.startActivity(intent);
                    }
                });

        builder.setNegativeButton (negativeTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        return builder.create();
    }
}
