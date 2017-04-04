package com.mcc.eshopper.activity.product;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.mcc.eshopper.R;
import com.mcc.eshopper.adapter.ProductCategoryAdapter;
import com.mcc.eshopper.api.helper.RequestCategoryData;
import com.mcc.eshopper.http.ResponseListener;
import com.mcc.eshopper.model.CategoryModel;

import java.util.ArrayList;

/**
 * Created by Nasir on 3/30/17.
 */
public class ProductCategory extends AppCompatActivity{
    ArrayList <CategoryModel> mCategoryList;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private  ProductCategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariable();
        initUI();
        initFunctionality();
//        initListener();
    }

    private void initListener() {

    }

    private void initFunctionality() {
        requestCategoryModels();
        loadModelsIntoUI();
    }

    private void loadModelsIntoUI() {
        categoryAdapter = new ProductCategoryAdapter(mContext, mCategoryList);

        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(categoryAdapter);

    }

    private void requestCategoryModels() {


        RequestCategoryData requestCategoryData = new RequestCategoryData(mContext);
        requestCategoryData.setResponseListener(new ResponseListener() {
            @Override
            public void onResponse(Object data) {
                mCategoryList = (ArrayList<CategoryModel>) data;
                categoryAdapter.updateList(mCategoryList);

            }
        });
        requestCategoryData.execute();

    }

    private void initUI() {

        setContentView(R.layout.activity_product_category);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_product_categories);
    }

    private void initVariable() {
        mCategoryList = new ArrayList<>();
        mContext = ProductCategory.this;
    }
}
