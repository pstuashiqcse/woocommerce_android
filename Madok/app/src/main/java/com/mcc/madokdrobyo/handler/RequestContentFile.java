package com.mcc.madokdrobyo.handler;

import android.content.Context;
import android.widget.Toast;

import com.mcc.madokdrobyo.constants.AppConsts;
import com.mcc.madokdrobyo.netWork.BaseNetWork;
import com.mcc.madokdrobyo.objects.ContentFile;
import com.mcc.madokdrobyo.parser.ContentFileParser;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestContentFile extends BaseNetWork{

    private Context mContext;
    private String menuIdValue;
    private ArrayList<ContentFile> contentFiles;
    private ContentFileParser parser;

    public RequestContentFile(Context mContext, String menuIdValue) {
        super(mContext);
        this.mContext = mContext;
        this.menuIdValue = menuIdValue;

        setRequestedUrl(getUrl(), AppConsts.REQUEST_METHOD_POST);
        setRequestParams(bindParameters());
    }

    public ArrayList<ContentFile> getContentFiles() {
        try {
            String response = super.get();
            onBackgroundTask(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return contentFiles;
    }

    private void onBackgroundTask(String response) {
        parser = new ContentFileParser(mContext);
        contentFiles = parser.parse(response);
    }

    private HashMap<String,String> bindParameters() {

        HashMap<String, String>params = new HashMap<>();
        params.put(AppConsts.KEY_APP_ID, AppConsts.VALUE_APP_ID);
        params.put(AppConsts.KEY_MENU_ID, menuIdValue);

        return params;
    }

    private String getUrl() {
        return AppConsts.BASE_URL + AppConsts.CONTENT_FILE_API;
    }


}
