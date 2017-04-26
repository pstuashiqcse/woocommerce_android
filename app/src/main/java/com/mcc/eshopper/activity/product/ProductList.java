package com.mcc.eshopper.activity.product;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import com.mcc.eshopper.R;
import com.mcc.eshopper.adapter.ProductListAdapter;
import com.mcc.eshopper.api.helper.RequestProductList;
import com.mcc.eshopper.http.ResponseListener;
import com.mcc.eshopper.model.ProductListModel;
import com.mcc.eshopper.utility.CustomAlertDialog;
import com.mcc.eshopper.utility.SystemUtility;

import java.util.ArrayList;

/**
 * Created by Nasir on 3/30/17.
 */

public class ProductList extends AppCompatActivity{

    //Data
    private Context mContext;
    private String selectedCategory;
    private ArrayList<ProductListModel> mProductList;


    //View
    private Toolbar mToolbar;
    private TextView tvCategoryName;
    private RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;
    private ProductListAdapter productListAdapter;
    private CustomAlertDialog mCustomAlertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariable();
        initUI();
        initFunctionality();
    }

    private void initVariable() {

        mContext = ProductList.this;
        mProductList = new ArrayList<>();
        selectedCategory = getIntent().getStringExtra("selected category");
        mProgressDialog = SystemUtility.showProgressDialog(mContext, "Loading. Please wait");
    }

    private void initUI() {
        setContentView(R.layout.activity_product_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_product_list);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        tvCategoryName = (TextView) findViewById(R.id.tv_top_bar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_product_list);
        tvCategoryName.setText(selectedCategory);

        productListAdapter = new ProductListAdapter(mContext, mProductList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(productListAdapter);
    }

    private void initFunctionality() {

        if (SystemUtility.isNetworkAvailable(mContext)){
            mProgressDialog.show();
            requestProductList();
        }
        else {
            mCustomAlertDialog.createDialog("Connect to internet", "Connect", "Cancel").show();
        }


    }

    private void requestProductList() {
        RequestProductList requestProductList = new RequestProductList(mContext, selectedCategory);
        requestProductList.setResponseListener(new ResponseListener() {
            @Override
            public void onResponse(Object data) {

                mProductList = (ArrayList<ProductListModel>) data;
                if (mProductList != null)
                    productListAdapter.updateList(mProductList);

                mProgressDialog.dismiss();
            }
        });
        requestProductList.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mProgressDialog.show();
//        requestProductList();
//    }
}
