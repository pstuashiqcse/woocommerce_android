package com.mcc.eshopper.activity.general;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.mcc.eshopper.R;
import com.mcc.eshopper.api.helper.RequestLogin;
import com.mcc.eshopper.appdata.AppConstants;
import com.mcc.eshopper.http.ResponseListener;
import com.mcc.eshopper.model.CategoryModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // init var
    private Context mContext;

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
        initListener();
    }

    private void initVariable(){
        mContext = MainActivity.this;
        productList = new ArrayList<>();

    }
    private void initUI(){
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }
    private void intitFunctionality(){
        loadCategoryList();

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
    protected void onDestroy() {
        super.onDestroy();
    }

    private void loadCategoryList() {
        RequestLogin requestLogin = new RequestLogin(MainActivity.this);
        requestLogin.setResponseListener(new ResponseListener() {
            @Override
            public void onResponse(Object data) {
                ArrayList<CategoryModel> arrayList = (ArrayList<CategoryModel>) data;

                for(CategoryModel categoryModel : arrayList) {
                    Log.e("Data: ", "CategoryModel: "+categoryModel.getName());
                }

            }
        });
        requestLogin.execute();
    }
}
