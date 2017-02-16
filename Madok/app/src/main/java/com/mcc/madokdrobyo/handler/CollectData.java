package com.mcc.madokdrobyo.handler;

import android.content.Context;
import android.widget.Toast;

import com.mcc.madokdrobyo.objects.Content;
import com.mcc.madokdrobyo.objects.ContentFile;

import java.util.ArrayList;

public class CollectData {

    private String menuId;
    private Context mContext;
    private ArrayList<Content> contents;
    private ArrayList<ContentFile> contentFiles;

    public CollectData(Context mContext, String menuId){
        this.mContext = mContext;
        this.menuId = menuId;
    }

    public void retrieveContentInformation(){
        RequestContent requestContent = new RequestContent(mContext, menuId);
        requestContent.execute();
        contents = requestContent.getContents();
    }

    public void retrieveContentFileInformation() {
        RequestContentFile requestContentFile = new RequestContentFile(mContext, menuId);
        requestContentFile.execute();
        contentFiles = requestContentFile.getContentFiles();
    }

    protected boolean isContent(){
        if (contents.size() == 1) return true;
        else return false;
    }

    public boolean contentIsEmpty(){
        if (contents.size() == 0) return true;
        else return false;
    }

    public boolean contentFileIsEmpty(){
        if (contentFiles.size() == 0) return true;
        else return false;
    }

    public ArrayList<Content> getContents() {
        return contents;
    }

    public ArrayList<ContentFile> getContentFiles() {
        return contentFiles;
    }
}
