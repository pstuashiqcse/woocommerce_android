package com.mcc.eshopper.api.parser;

import com.mcc.eshopper.model.CategoryModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ashiq on 8/13/2016.
 */
public class CategoryParser {

    public ArrayList<CategoryModel> getCategoryModels(String response) {
        try {

            ArrayList<CategoryModel> arrayList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response);

            for(int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = 0;
                String name = null, image = null;

                if (jsonObject.has(ParserKey.KEY_ID)) {
                    id = jsonObject.getInt(ParserKey.KEY_ID);
                }
                if (jsonObject.has(ParserKey.KEY_NAME)) {
                    name = jsonObject.getString(ParserKey.KEY_NAME);
                }
                if (jsonObject.has(ParserKey.KEY_IMAGE)) {
                    image = jsonObject.getString(ParserKey.KEY_IMAGE);
                }

                arrayList.add(new CategoryModel(id, name, image));

            }

            return arrayList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
