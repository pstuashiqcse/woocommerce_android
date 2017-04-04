package com.mcc.eshopper.api.helper;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mcc.eshopper.api.params.HttpParams;
import com.mcc.eshopper.api.parser.CategoryParser;
import com.mcc.eshopper.http.BaseHttp;
import com.mcc.eshopper.http.ResponseListener;
import com.mcc.eshopper.model.CategoryModel;

import java.util.ArrayList;

/**
 * Created by Nasir on 3/30/17.
 */
public class RequestCategoryData extends BaseHttp{

    private ArrayList<CategoryModel> categoryModels;
    private ResponseListener responseListener;

    public RequestCategoryData(Context context) {
        super(context, HttpParams.categoriesApi());

    }

    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    public void onBackgroundTask(String response) {


        CategoryParser categoryParser = new CategoryParser();
        categoryModels = categoryParser.getCategoryModels(response);


    }

    @Override
    public void onTaskComplete() {
        if (responseListener != null){
            responseListener.onResponse(categoryModels);
        }
    }


}
