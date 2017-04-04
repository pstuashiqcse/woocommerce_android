package com.mcc.eshopper.activity.product;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcc.eshopper.R;
import com.mcc.eshopper.api.helper.RequestProductData;
import com.mcc.eshopper.http.ResponseListener;
import com.mcc.eshopper.model.ProductModel;

/**
 * Created by Nasir on 3/30/17.
 */
public class ProductDetails extends AppCompatActivity{
    private int mProductId;
    private ProductModel mProduct;
    private Context mContext;

    //view
    private TextView mProductName;
    private TextView mProductPrice;
    private ImageView mProductImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariable();
        initUI();
        initFunctionality();
//      initListener();
    }

    private void initVariable() {
        mProductId = getIntent().getIntExtra("id", 0);
        mContext = ProductDetails.this;
    }

    private void initUI() {
        setContentView(R.layout.activity_product_details);
        mProductName = (TextView) findViewById(R.id.tv_product_name);
        mProductPrice = (TextView) findViewById(R.id.tv_product_price);
        mProductImage = (ImageView) findViewById(R.id.iv_product_image);
    }

    private void initFunctionality() {
        requestProductDetails();
    }

    private void requestProductDetails() {
        RequestProductData requestProductData = new RequestProductData(mContext, mProductId);
        requestProductData.setResponseListener(new ResponseListener() {
            @Override
            public void onResponse(Object data) {
                mProduct = (ProductModel) data;
                insertIntoUI();
            }
        });
    }

    private void insertIntoUI() {
        mProductName.setText(mProduct.getTitle());
        mProductPrice.setText(mProduct.getPrice());

        String url = mProduct.getImage();
        Glide.with(mContext)
                .load(url)
                .thumbnail(.1f)
                .into(mProductImage);
    }
}
