package com.mcc.eshopper.activity.product;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mcc.eshopper.R;
import com.mcc.eshopper.adapter.ProductCategoryAdapter;
import com.mcc.eshopper.api.helper.RequestCategoryData;
import com.mcc.eshopper.http.ResponseListener;
import com.mcc.eshopper.model.CategoryModel;
import com.mcc.eshopper.utility.CustomAlertDialog;
import com.mcc.eshopper.utility.SystemUtility;

import java.util.ArrayList;

/**
 * Created by Nasir on 3/30/17.
 */
public class ProductCategory extends AppCompatActivity{

    private Toolbar mToolbar;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private  ProductCategoryAdapter categoryAdapter;
    private ArrayList <CategoryModel> mCategoryList;

    private CustomAlertDialog mCustomAlertDialog;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariable();
        initUI();
        initFunctionality();
    }

    private void initVariable() {

        mContext = ProductCategory.this;
        mCategoryList = new ArrayList<>();
        mCustomAlertDialog = new CustomAlertDialog(mContext);
        mProgressDialog = SystemUtility.showProgressDialog(mContext, "Loading. Please wait");
    }

    private void initUI() {
        setContentView(R.layout.activity_product_category);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_product_category);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        categoryAdapter = new ProductCategoryAdapter(mContext, mCategoryList);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_product_categories);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(categoryAdapter);
    }

    private void initFunctionality() {

        if (SystemUtility.isNetworkAvailable(mContext)){
            loadCategoryData();
            mProgressDialog.show();
        }
        else {
            mCustomAlertDialog.createDialog("Connect to internet", "Connect", "Cancel").show();
        }
    }

    private void loadCategoryData() {

        RequestCategoryData requestCategoryData = new RequestCategoryData(mContext);
        requestCategoryData.setResponseListener(new ResponseListener() {
            @Override
            public void onResponse(Object data) {

                mCategoryList = (ArrayList<CategoryModel>) data;

                if(mCategoryList != null){
                    mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    categoryAdapter = new ProductCategoryAdapter(mContext, mCategoryList);
                    mRecyclerView.setAdapter(categoryAdapter);
                }
                //categoryAdapter.updateList(mCategoryList);
                mProgressDialog.dismiss();
            }
        });
        requestCategoryData.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCategoryData();
        mProgressDialog.show();
    }
}
