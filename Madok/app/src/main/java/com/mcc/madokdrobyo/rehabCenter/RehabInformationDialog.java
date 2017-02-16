package com.mcc.madokdrobyo.rehabCenter;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mcc.madokdrobyo.R;
import com.mcc.madokdrobyo.constants.AppConsts;

public class RehabInformationDialog extends DialogFragment {

    private String title;
    private String address;
    private String contact;

    public void setData(String title, String address, String contact) {
        this.title = title;
        this.address = address;
        this.contact = contact;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context mContext = getActivity();
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.content_rehab_info_dialog, null, false);

        TextView tvRehabName = (TextView) view.findViewById(R.id.tvrehabName);
        TextView tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);

        final TextView tvcontact = (TextView) view.findViewById(R.id.tvContact);


        tvRehabName.setText(title);
        tvAddress.setText(address);
        tvcontact.setText(contact);

        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Address", Toast.LENGTH_LONG ).show();
            }
        });

        tvcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
                processContacts(tvcontact.getText().toString());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });

        return new android.app.AlertDialog.Builder(mContext)
                        .setView(view)
                        .create();



    }

    private void processContacts(String contacts) {

        RehabContactDialog contactDialog = new RehabContactDialog();
        contactDialog.processContacts(contacts);
        contactDialog.show(getFragmentManager(), AppConsts.CONTACT_FRAGMENT_TAG);
    }

}
