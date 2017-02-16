package com.mcc.madokdrobyo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mcc.madokdrobyo.R;
import com.mcc.madokdrobyo.adapter.ListAdapter;
import com.mcc.madokdrobyo.constants.AppConsts;
import com.mcc.madokdrobyo.objects.Content;
import com.mcc.madokdrobyo.objects.RehabInfoModel;
import com.mcc.madokdrobyo.rehabCenter.RehabInformation;
import com.mcc.madokdrobyo.rehabCenter.RehabInformationDialog;


import java.util.ArrayList;

public class GetContentActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<Content>contents;
    private RehabInfoModel mRehab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_content);

        contents = (ArrayList<Content>) getIntent().getSerializableExtra("Contents");
        setToolbar();
        ShowContents();

        if( isListOfCOntents() ){
            setOnClickListener();
        }
    }

    private void setToolbar() {
        TextView textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText(contents.get(0).getTitle());
    }

    private void ShowContents() {
        mListView = (ListView) findViewById(R.id.lvContent);
        mListView.setAdapter(new ListAdapter(this, contents));
    }

    private boolean isListOfCOntents() {

        if (contents.size()>1)
            return true;
        else return false;
    }

    private void setOnClickListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                processRehabinfo(contents.get(position).getDetails().toString());
                showDialogue();
            }
        });
    }

    private void showDialogue() {

        RehabInformationDialog dialog = new RehabInformationDialog();
        dialog.setData(mRehab.getRehabName(), mRehab.getRehabAdderss(), mRehab.getRehabContactInfo());
        dialog.show(getFragmentManager(), AppConsts.REHAB_INFORMATION);
    }

    private void processRehabinfo(String strRehabInfo) {

        RehabInformation rehabInformation = new RehabInformation(strRehabInfo);
        rehabInformation.transformIntoRehabModel();
        mRehab = rehabInformation.getRehabInfo();
    }
}
