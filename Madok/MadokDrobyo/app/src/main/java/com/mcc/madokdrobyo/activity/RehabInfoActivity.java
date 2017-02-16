package com.mcc.madokdrobyo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mcc.madokdrobyo.R;
import com.mcc.madokdrobyo.adapter.RehabAdapter;
import com.mcc.madokdrobyo.objects.RehabInfoModel;
import com.mcc.madokdrobyo.rehabCenter.RehabInformation;

public class RehabInfoActivity extends AppCompatActivity {
    private String strRehabInfo;
    private RehabInfoModel mRehab;
    private RehabAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private TextView tvRehabName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehab_info);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvRehabInfo);
        tvRehabName = (TextView) findViewById(R.id.tvrehabName);

        strRehabInfo = getIntent().getStringExtra("rehabInfo");
        RehabInformation processRehabInformation = new RehabInformation(strRehabInfo);
        processRehabInformation.transformIntoRehabModel();

        mRehab = processRehabInformation.getRehabInfo();
        tvRehabName.setText(mRehab.getRehabName());
        mAdapter = new RehabAdapter(mRehab);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }
}
