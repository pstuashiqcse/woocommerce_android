package com.mcc.eshopper.api.parser;

import com.mcc.eshopper.model.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nitul on 4/4/17.
 */

public class ProductDataParser {

    public ProductModel getProductData(String response){
        ProductModel productDetail;

        ArrayList<String> category = new ArrayList<>();
        String name = null, image = null, price = null, salePrice = null, regularPrice = null;

        try {
            JSONObject jsonObject = new JSONObject(response);

            if (jsonObject.has(ParserKey.KEY_NAME))
                name = jsonObject.getString(ParserKey.KEY_NAME);

            if (jsonObject.has(ParserKey.KEY_PRODUCT_CATEGORIES))
                category = getCategory(jsonObject.getJSONArray(ParserKey.KEY_PRODUCT_CATEGORIES));

            if (jsonObject.has(ParserKey.KEY_PRODUCT_PRICE))
                price = jsonObject.getString(ParserKey.KEY_PRODUCT_PRICE);

            if (jsonObject.has("regular_price")) {
                String rPrice = jsonObject.getString(ParserKey.KEY_PRODUCT_REGULAR_PRICE);
                if (!rPrice.equals(""))
                    regularPrice = rPrice;
            }

            if (jsonObject.has(ParserKey.KEY_PRODUCT_SALE_PRICE)) {
                String data = jsonObject.getString(ParserKey.KEY_PRODUCT_SALE_PRICE);
                if (!data.equals("")){
                    salePrice = data;
                }
            }

            if (jsonObject.has(ParserKey.KEY_PRODUCT_IMAGES))
                image = getImageurl(jsonObject.getJSONArray(ParserKey.KEY_PRODUCT_IMAGES));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        productDetail = new ProductModel(name, category, price, regularPrice, salePrice, image);
        return productDetail;
    }

    private  ArrayList<String> getCategory(JSONArray jsonArray) {
        ArrayList<String> categories = new ArrayList<>();

        for (int index = 0; index<jsonArray.length(); index++){

            try {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                if (jsonObject.has(ParserKey.KEY_NAME))
                    categories.add(jsonObject.getString(ParserKey.KEY_NAME));

            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return categories;
    }

    private String getImageurl(JSONArray jsonArray) {
        String url = null;
        try {
            JSONObject imageObject = jsonArray.getJSONObject(0);

            if (imageObject.has(ParserKey.KEY_IMAGE_SOURCE))
                url = imageObject.getString(ParserKey.KEY_IMAGE_SOURCE);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return url;
    }
}
