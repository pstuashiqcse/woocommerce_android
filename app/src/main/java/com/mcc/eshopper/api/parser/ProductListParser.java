package com.mcc.eshopper.api.parser;

import android.util.Log;
import com.mcc.eshopper.model.ProductListModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by nitul on 4/3/17.
 */

public class ProductListParser {

    public ArrayList <ProductListModel> getProductList(String response, String selectedcategory){
        ArrayList <ProductListModel> products = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int index=0; index<jsonArray.length(); index++){

                JSONObject jsonObject = jsonArray.getJSONObject(index);
                int id = 0;
                String name = null, image = null;

                if (containscategory(jsonObject, selectedcategory)){

                    if (jsonObject.has(ParserKey.KEY_ID))
                        id = jsonObject.getInt(ParserKey.KEY_ID);

                    if (jsonObject.has(ParserKey.KEY_NAME))
                        name = jsonObject.getString(ParserKey.KEY_NAME);

                    if (jsonObject.has(ParserKey.KEY_PRODUCT_IMAGES))
                        image = getImageurl(jsonObject.getJSONArray(ParserKey.KEY_PRODUCT_IMAGES));

                    Log.d("Image url", "getProductList: " + image);
                    products.add(new ProductListModel(id, name, image));
                }


                Log.e("TAG", "getProductList: "+ products.size() );

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return products;
    }

    private String getImageurl(JSONArray jsonArray) {

        try {
            JSONObject imageObject = jsonArray.getJSONObject(0);
            if (imageObject.has(ParserKey.KEY_IMAGE_SOURCE))
                return imageObject.getString(ParserKey.KEY_IMAGE_SOURCE);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean containscategory(JSONObject jsonObject, String selectedcategory) {
        boolean flag = false;
        if (jsonObject.has(ParserKey.KEY_PRODUCT_CATEGORIES)){

            try {
                JSONArray array = jsonObject.getJSONArray(ParserKey.KEY_PRODUCT_CATEGORIES);
                for (int index = 0; index<array.length(); index++){

                    JSONObject category = array.getJSONObject(index);
                    if (category.getString(ParserKey.KEY_NAME).equals(selectedcategory)){
                        flag = true;
                    }
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
