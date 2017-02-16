package com.mcc.madokdrobyo.rehabCenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.mcc.madokdrobyo.constants.AppConsts;

/**
 * Created by nitul on 1/31/17.
 */

public class RehabContactDialog extends DialogFragment {

    private String[] arrContacts;

    public void processContacts(String contacts){
        this.arrContacts = contacts.split(", ");

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(AppConsts.CONTACT_TITLE)
                .setItems(arrContacts, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDialog().cancel();
                        openDialer(which);
                    }
                });

        AlertDialog dialog = builder.create();
        return dialog;
    }

    private void openDialer(int index) {

        String phone = arrContacts[index];
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(phoneIntent);
    }
}
