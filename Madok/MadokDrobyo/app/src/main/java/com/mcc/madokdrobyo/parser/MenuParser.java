package com.mcc.madokdrobyo.parser;

import android.content.Context;

import com.mcc.madokdrobyo.constants.ParseKey;
import com.mcc.madokdrobyo.objects.MainMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nitul on 1/23/17.
 */

public class MenuParser {
    private ArrayList<MainMenu>menus;
    private Context mContext;

    public MenuParser(Context mContext){
        this.mContext = mContext;
    }

    public void parseMenuString(String response) {
        menus = new ArrayList<>();

        try {
            if (response != null && !response.isEmpty()) {


                JSONArray jsonArray = new JSONObject(response).getJSONArray(ParseKey.KEY_MAIN_MENU);
                for (int index = 0; index < jsonArray.length(); index++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(index);

                    if (jsonObject != null) {

                        String menuId = null, name = null, status = null, img = null;

                        if (jsonObject.has(ParseKey.KEY_MENU_ID))
                            menuId = jsonObject.getString(ParseKey.KEY_MENU_ID);

                        if (jsonObject.has(ParseKey.KEY_MENU_NAME))
                            name = jsonObject.getString(ParseKey.KEY_MENU_NAME);

                        if (jsonObject.has(ParseKey.KEY_MENU_STATUS))
                            status = jsonObject.getString(ParseKey.KEY_MENU_STATUS);

                        if (jsonObject.has(ParseKey.KEY_MENU_IMG))
                        img = jsonObject.getString(ParseKey.KEY_MENU_IMG);

                        menus.add(new MainMenu(menuId, name, status, img));
                    }
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MainMenu> getMenus() {
        return menus;
    }
}
