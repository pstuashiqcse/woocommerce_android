package com.mcc.eshopper.activity.product;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcc.eshopper.R;
import com.mcc.eshopper.api.helper.RequestProductData;
import com.mcc.eshopper.http.ResponseListener;
import com.mcc.eshopper.model.ProductModel;
import com.mcc.eshopper.utility.CustomAlertDialog;
import com.mcc.eshopper.utility.SystemUtility;

import java.util.ArrayList;

/**
 * Created by Nasir on 3/30/17.
 */
public class ProductDetails extends AppCompatActivity{

    private Context mContext;
    private int mProductId;
    private ProductModel mProduct;
    private ProgressDialog mProgressDialog;

    //view
    private Toolbar mToolbar;
    private TextView mTopBar;
    private TextView mProductName;
    private TextView mProductCategory;
    private TextView mProductRegularPrice;
    private TextView mProductSalePrice;
    private ImageView mProductImage;
    private CustomAlertDialog mCustomAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariable();
        initUI();
        initFunctionality();
    }

    private void initVariable() {

        mContext = ProductDetails.this;
        mProductId = getIntent().getIntExtra("id", 0);
        mProgressDialog = SystemUtility.showProgressDialog(mContext, "Loading. Please wait");
    }

    private void initUI() {
        setContentView(R.layout.activity_product_details);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_product_details);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mTopBar = (TextView) findViewById(R.id.tv_top_bar);
        mProductName = (TextView) findViewById(R.id.tv_product_name);
        mProductCategory = (TextView) findViewById(R.id.tv_product_category);
        mProductRegularPrice = (TextView) findViewById(R.id.tv_regular_price);
        mProductSalePrice = (TextView) findViewById(R.id.tv_sale_price);
        mProductImage = (ImageView) findViewById(R.id.iv_product_image);
    }

    private void initFunctionality() {


        if (SystemUtility.isNetworkAvailable(mContext)){
            requestProductDetails();
            mProgressDialog.show();
        }
        else {
            mCustomAlertDialog.createDialog("Connect to internet", "Connect", "Cancel").show();
        }
    }

    private void requestProductDetails() {
        RequestProductData requestProductData = new RequestProductData(mContext, mProductId);
        requestProductData.setResponseListener(new ResponseListener() {
            @Override
            public void onResponse(Object data) {

                mProduct = (ProductModel) data;
                if (mProduct != null)
                    insertIntoUI();

                mProgressDialog.dismiss();
            }
        });
        requestProductData.execute();
    }

    private void insertIntoUI() {

//        mTopBar.setText(mProduct.getTitle());
        mProductName.setText(mProduct.getTitle());
        mProductCategory.setText (getCategoryString());
        mProductRegularPrice.setText("BDT " + mProduct.getRegularPrice());

        if (mProduct.getSalePrice() != null){
            mProductRegularPrice.setBackgroundResource(R.drawable.drawable_line);
            mProductSalePrice.setText("BDT " + mProduct.getSalePrice());
        }

        String url = mProduct.getImage();
        Glide.with(mContext)
                .load(url)
                .thumbnail(.1f)
                .into(mProductImage);
    }

    private String getCategoryString() {

        ArrayList <String> categorylist = mProduct.getCategory();
        StringBuilder categories = new StringBuilder();

        for (int index=0; index<categorylist.size(); index++){
            categories.append(categorylist.get(index));
            if (index > 0){
                categories.append(", ");
            }
        }
        return categories.toString();
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
//        requestProductDetails();
//        mProgressDialog.show();
//    }
}
