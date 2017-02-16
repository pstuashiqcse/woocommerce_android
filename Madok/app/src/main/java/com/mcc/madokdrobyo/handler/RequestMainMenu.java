package com.mcc.madokdrobyo.handler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mcc.madokdrobyo.constants.AppConsts;
import com.mcc.madokdrobyo.netWork.BaseNetWork;
import com.mcc.madokdrobyo.objects.MainMenu;
import com.mcc.madokdrobyo.parser.MenuParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class RequestMainMenu extends BaseNetWork{

    private Context mContext;
    private MenuParser parser;
    private ArrayList <MainMenu> menus;


    public RequestMainMenu(Context mContext){
        super(mContext);
        this.mContext = mContext;

        setRequestedUrl(getURL(), AppConsts.REQUEST_METHOD_POST);
        setRequestParams( bindParameters() );
    }

    public void onBackgroundTask(String responseString) {

            parser = new MenuParser(mContext);
            parser.parseMenuString(responseString);
            menus = parser.getMenus();
    }


    public ArrayList <MainMenu> getMenus(){
        try {
            String response = super.get();
            onBackgroundTask(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }

    private HashMap< String,String > bindParameters() {

        HashMap<String, String>params = new HashMap<>();
        params.put(AppConsts.KEY_APP_ID, AppConsts.VALUE_APP_ID);
        return params;
    }

    public String getURL() {
        return AppConsts.BASE_URL + AppConsts.MENU_API;
    }
}
