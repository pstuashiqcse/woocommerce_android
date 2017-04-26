package com.mcc.eshopper.activity.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mcc.eshopper.R;
import com.mcc.eshopper.activity.product.ProductCategory;

/**
 * Created by Nasir on 3/30/17.
 */

public class SplashActivity extends AppCompatActivity{

    private Activity mActivity;
    private static final int DURATION_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mActivity = SplashActivity.this;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(DURATION_TIME);

                    startActivity(new Intent(mActivity, ProductCategory.class));
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}