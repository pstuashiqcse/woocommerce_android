package com.mcc.madokdrobyo.handler;

import android.content.Context;

import com.mcc.madokdrobyo.constants.AppConsts;
import com.mcc.madokdrobyo.netWork.BaseNetWork;
import com.mcc.madokdrobyo.objects.Content;
import com.mcc.madokdrobyo.objects.MainMenu;
import com.mcc.madokdrobyo.parser.ContentParser;
import com.mcc.madokdrobyo.parser.MenuParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class RequestContent extends BaseNetWork{

    private Context mContext;
    private String menuIdValue;

    private ArrayList <Content> contents;
    private ContentParser parser;

    public RequestContent(Context mContext, String menuIdValue){
        super(mContext);
        this.mContext = mContext;
        this.menuIdValue = menuIdValue;

        setRequestedUrl(getUrl(), AppConsts.REQUEST_METHOD_POST);
        setRequestParams(bindParameters());
    }

    public void onBackgroundTask(String responseString) {

        parser = new ContentParser(mContext);
        contents = parser.parse(responseString);
    }

    public ArrayList<Content> getContents() {

        try {
            String response = super.get();
            onBackgroundTask(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }

    private HashMap<String,String> bindParameters() {

        HashMap<String, String>params = new HashMap<>();
        params.put(AppConsts.KEY_APP_ID, AppConsts.VALUE_APP_ID);
        params.put(AppConsts.KEY_MENU_ID, menuIdValue);

        return params;
    }

    public String getUrl() {
        return AppConsts.BASE_URL+ AppConsts.CONTENT_API;
    }


}
