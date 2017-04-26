package com.mcc.eshopper.api.helper;

import android.content.Context;
import android.content.IntentFilter;

import com.mcc.eshopper.api.params.HttpParams;
import com.mcc.eshopper.api.parser.ProductDataParser;
import com.mcc.eshopper.http.BaseHttp;
import com.mcc.eshopper.http.ResponseListener;
import com.mcc.eshopper.model.ProductModel;

/**
 * Created by Nasir on 3/30/17.
 */
public class RequestProductData extends BaseHttp{
    private ProductModel mProductModel;
    private ResponseListener responseListener;

    public RequestProductData(Context context, int id) {
        super(context, HttpParams.productDetailsApi(id));
    }


    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    public void onBackgroundTask(String response) {
        ProductDataParser productDataParser = new ProductDataParser();
        mProductModel = productDataParser.getProductData(response);
    }

    @Override
    public void onTaskComplete() {
        if (responseListener != null)
            responseListener.onResponse(mProductModel);
    }
}
