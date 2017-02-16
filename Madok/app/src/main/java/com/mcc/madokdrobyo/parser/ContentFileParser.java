package com.mcc.madokdrobyo.parser;

import android.content.Context;

import com.mcc.madokdrobyo.constants.ParseKey;
import com.mcc.madokdrobyo.objects.ContentFile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nitul on 2/1/17.
 */

public class ContentFileParser {
    Context mContext;

    public ContentFileParser(Context mContext){
        this.mContext = mContext;
    }

    public ArrayList<ContentFile> parse(String response){
        ArrayList<ContentFile> contents = new ArrayList<>();

        try {
            if (response != null && !response.isEmpty()){
                JSONArray jsonArray = new JSONObject(response).getJSONArray(ParseKey.KEY_CONTENT_FILE);

                for (int index = 0; index < jsonArray.length(); index++){
                    JSONObject jsonObject = jsonArray.getJSONObject(index);

                    if (jsonObject != null){
                        String id = null, title = null, ytLink = null, image = null;

                        if (jsonObject.has(ParseKey.KEY_CONTENT_FILE_ID))
                            id = jsonObject.getString(ParseKey.KEY_CONTENT_FILE_ID);

                        if (jsonObject.has(ParseKey.KEY_CONTENT_FILE_TITLE))
                            title = jsonObject.getString(ParseKey.KEY_CONTENT_FILE_TITLE);

                        if (jsonObject.has(ParseKey.KEY_CONTENT_FILE_YT_LINK))
                            ytLink = jsonObject.getString(ParseKey.KEY_CONTENT_FILE_YT_LINK);

                        if (jsonObject.has(ParseKey.KEY_CONTENT_FILE_IMAGE))
                            image = jsonObject.getString(ParseKey.KEY_CONTENT_FILE_IMAGE);

                        contents.add(new ContentFile(id, title, ytLink, image));

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
