package com.mcc.eshopper.api.helper;

import android.content.Context;
import android.util.Log;


import com.mcc.eshopper.api.params.HttpParams;
import com.mcc.eshopper.api.parser.CategoryParser;
import com.mcc.eshopper.http.BaseHttp;
import com.mcc.eshopper.http.ResponseListener;
import com.mcc.eshopper.model.CategoryModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ashiq on 9/23/2016.
 */
public class RequestLogin extends BaseHttp {

    private Object object;
    private ResponseListener responseListener;

    public RequestLogin(Context context) {
        super(context, HttpParams.categoriesApi());
    }

    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    public void onBackgroundTask(String response) {
        //object = response;
        Log.e("Response", "Login: " + response);

        ArrayList<CategoryModel> categoryModels = new CategoryParser().getCategoryModels(response);
        object = categoryModels;
    }

    @Override
    public void onTaskComplete() {
        if (responseListener != null) {
            responseListener.onResponse(object);
        }
    }
}
