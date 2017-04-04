package com.mcc.eshopper.api.helper;

import android.content.Context;

import com.mcc.eshopper.api.params.HttpParams;
import com.mcc.eshopper.api.parser.ProductListParser;
import com.mcc.eshopper.http.BaseHttp;
import com.mcc.eshopper.http.ResponseListener;
import com.mcc.eshopper.model.ProductListModel;

import java.util.ArrayList;

/**
 * Created by nitul on 4/3/17.
 */

public class RequestProductList extends BaseHttp {

    private ArrayList <ProductListModel> products;
    private ResponseListener responseListener;
    private String selectedCategory;

    public RequestProductList(Context context, String selectedCategory) {
        super(context, HttpParams.categoryItemApi());
        this.selectedCategory = selectedCategory;
    }

    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    public void onBackgroundTask(String response) {
        ProductListParser productListParser = new ProductListParser();
        products = productListParser.getProductList(response, selectedCategory);
    }

    @Override
    public void onTaskComplete() {
        if (responseListener != null)
            responseListener.onResponse(products);
    }
}
