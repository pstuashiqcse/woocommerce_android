package com.mcc.madokdrobyo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mcc.madokdrobyo.R;
import com.mcc.madokdrobyo.adapter.VideoAdapter;
import com.mcc.madokdrobyo.handler.RequestContentFile;
import com.mcc.madokdrobyo.objects.ContentFile;
import com.mcc.madokdrobyo.parser.ContentFileParser;

import java.util.ArrayList;

public class GetContentFileActivity extends AppCompatActivity {

    private ArrayList<ContentFile> videos;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_content_file);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvFile);
        videos = (ArrayList<ContentFile>) getIntent().getSerializableExtra("ContentFiles");

        if (videos != null && !videos.isEmpty())
            showContents();
    }


    private void showContents() {
        VideoAdapter mAdapter = new VideoAdapter(this,videos);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

    }

}
