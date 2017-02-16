package com.mcc.madokdrobyo.parser;

import android.content.Context;

import com.mcc.madokdrobyo.constants.ParseKey;
import com.mcc.madokdrobyo.objects.Content;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nitul on 1/25/17.
 */

public class ContentParser {
    private Context mContext;

    public ContentParser(Context mContext){
        this.mContext = mContext;
    }

    public ArrayList<Content> parse(String response){
        ArrayList<Content> contents = new ArrayList<>();

        try {

            if (response!= null && !response.isEmpty()){
                JSONArray jsonArray = new JSONObject(response).getJSONArray(ParseKey.KEY_CONTENT_LIST);

                for (int index = 0; index < jsonArray.length(); index++){
                    JSONObject jsonObject = jsonArray.getJSONObject(index);

                    if (jsonObject != null){
                        String contentId = null, title = null, details = null;

                        if (jsonObject.has(ParseKey.KEY_CONTENT_ID))
                            contentId = jsonObject.getString(ParseKey.KEY_CONTENT_ID);

                        if (jsonObject.has(ParseKey.KEY_CONTENT_TITLE))
                            title = jsonObject.getString(ParseKey.KEY_CONTENT_TITLE);

                        if (jsonObject.has(ParseKey.KEY_CONTENT_DETAILS))
                            details = jsonObject.getString(ParseKey.KEY_CONTENT_DETAILS);

                        contents.add(new Content(contentId, title, details));
                    }
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return contents;
    }
}
