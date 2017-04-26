package com.mcc.eshopper.activity.general;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.mcc.eshopper.R;
import com.mcc.eshopper.activity.product.ProductCategory;
import com.mcc.eshopper.utility.CustomAlertDialog;
import com.mcc.eshopper.utility.SystemUtility;

import java.util.ArrayList;

@Deprecated
public class MainActivity extends AppCompatActivity {

    // init var
    private Context mContext;
    private CustomAlertDialog mFragmentUtils;

    // init ui
    private FloatingActionButton fab;
    private Button btnLogin;

    Toolbar toolbar;
    // init list
    private ArrayList<String> productList;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariable();
        initUI();
        intitFunctionality();
//      initListener();
    }

    private void initVariable(){
        mContext = MainActivity.this;
        mFragmentUtils = new CustomAlertDialog(mContext);

    }

    private void initUI(){
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void intitFunctionality(){
        if (SystemUtility.isNetworkAvailable(mContext)){
            showCategory();
        }
        else {
           // mFragmentUtils.createDialog().show();
        }

    }

    private void showCategory() {
        Intent intent = new Intent(this, ProductCategory.class);
        startActivity(intent);
        this.finish();
    }

    private void initListener(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        intitFunctionality();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
