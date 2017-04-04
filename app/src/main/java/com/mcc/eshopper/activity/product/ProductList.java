package com.mcc.eshopper.activity.product;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import com.mcc.eshopper.R;
import com.mcc.eshopper.adapter.ProductListAdapter;
import com.mcc.eshopper.api.helper.RequestProductList;
import com.mcc.eshopper.http.ResponseListener;
import com.mcc.eshopper.model.ProductListModel;
import java.util.ArrayList;

/**
 * Created by Nasir on 3/30/17.
 */
public class ProductList extends AppCompatActivity{
    private String selectedCategory;
    private ArrayList<ProductListModel> mProductList;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private ProductListAdapter productListAdapter;
    private TextView tvCategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariable();
        initUI();
        initFunctionality();
//      initListener();
    }

    private void initVariable() {
        mProductList = new ArrayList<>();
        selectedCategory = getIntent().getStringExtra("selected category");
        mContext = ProductList.this;

    }
    private void initUI() {
        setContentView(R.layout.activity_product_list);
        tvCategoryName = (TextView) findViewById(R.id.tv_top_bar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_product_list);
        tvCategoryName.setText(selectedCategory);
    }
    private void initFunctionality() {
        requestProductList();
        loadModelsIntoUI();

    }

    private void loadModelsIntoUI() {
        productListAdapter = new ProductListAdapter(mContext, mProductList);

        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(productListAdapter);
    }

    private void requestProductList() {
        RequestProductList requestProductList = new RequestProductList(mContext, selectedCategory);
        requestProductList.setResponseListener(new ResponseListener() {
            @Override
            public void onResponse(Object data) {
                mProductList = (ArrayList<ProductListModel>) data;
                productListAdapter.updateList(mProductList);
                Log.d("Request products", "onResponse: " + mProductList.size());
            }
        });
        requestProductList.execute();
    }

}
