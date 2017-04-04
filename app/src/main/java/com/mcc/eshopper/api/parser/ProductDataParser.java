package com.mcc.eshopper.api.parser;

import com.mcc.eshopper.model.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nitul on 4/4/17.
 */

public class ProductDataParser {

    public ProductModel getProductData(String response){
        ProductModel productDetail;

        String title = null, image = null;
        int price = 0;

        try {
            JSONObject jsonObject = new JSONObject(response);

            if (jsonObject.has(ParserKey.KEY_PRODUCT_TITLE))
                title = jsonObject.getString(ParserKey.KEY_PRODUCT_TITLE);

            if (jsonObject.has(ParserKey.KEY_PRODUCT_PRICE))
                price = jsonObject.getInt(ParserKey.KEY_PRODUCT_PRICE);

            if (jsonObject.has(ParserKey.KEY_PRODUCT_IMAGES))
                image = getImageurl(jsonObject.getJSONArray(ParserKey.KEY_PRODUCT_IMAGES));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        productDetail = new ProductModel(title, price, image);
        return productDetail;
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
}
