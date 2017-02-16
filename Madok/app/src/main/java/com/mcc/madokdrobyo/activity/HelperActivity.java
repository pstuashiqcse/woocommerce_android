package com.mcc.madokdrobyo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mcc.madokdrobyo.R;
import com.mcc.madokdrobyo.handler.CollectData;
import com.mcc.madokdrobyo.objects.Content;
import com.mcc.madokdrobyo.objects.ContentFile;

import java.util.ArrayList;

public class HelperActivity extends AppCompatActivity {
    private String menuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);

        menuId = getIntent().getStringExtra("MenuId");
        doOnBackground();
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }

    private void doOnBackground() {
        CollectData data = new CollectData(HelperActivity.this, menuId);
        data.retrieveContentInformation();

        if (!data.contentIsEmpty()){
            ArrayList<Content> contents = data.getContents();
            startContentActivity(contents);

        }
        else {
            retrieveContentFileInfo(data);
        }
    }

    private void retrieveContentFileInfo(CollectData data) {
        data.retrieveContentFileInformation();

        if (!data.contentFileIsEmpty()){
            ArrayList<ContentFile> contentFiles = data.getContentFiles();
            startContentFileActivity(contentFiles);
        }
        else startMapActivity();
    }

    private void startContentFileActivity(ArrayList<ContentFile> contentFiles){
        Intent intent = new Intent(HelperActivity.this, GetContentFileActivity.class);
        intent.putExtra("ContentFiles", contentFiles);
        startActivity(intent);
    }

    private void startContentActivity(ArrayList<Content> contents) {
        Intent intent = new Intent(HelperActivity.this, GetContentActivity.class);
        intent.putExtra("Contents", contents);
        startActivity(intent);
    }

    private void startMapActivity() {
        Intent intent = new Intent(HelperActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}
