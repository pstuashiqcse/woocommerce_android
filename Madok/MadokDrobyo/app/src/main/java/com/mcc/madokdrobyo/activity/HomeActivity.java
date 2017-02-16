package com.mcc.madokdrobyo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.mcc.madokdrobyo.R;
import com.mcc.madokdrobyo.adapter.GridAdapter;
import com.mcc.madokdrobyo.constants.AppConsts;
import com.mcc.madokdrobyo.handler.RequestMainMenu;
import com.mcc.madokdrobyo.objects.MainMenu;
import com.mcc.madokdrobyo.netWork.SystemInformation;
import com.mcc.madokdrobyo.utils.Permissions;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    private ArrayList<MainMenu> menus = new ArrayList<>();
    private SystemInformation system;
    private Permissions permissionStatus;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading");
        mProgressDialog.setCancelable(true);

        permissionStatus = new Permissions();
        system = new SystemInformation(this);

        if (system.supportRuntimeRequest()){
            requestRuntimePermissions();
        }

    }

    private void requestRuntimePermissions() {

        if( !permissionStatus.isLocationAccessAllowed(HomeActivity.this)) {
            permissionStatus.generatePermissionRequest(HomeActivity.this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        connectionInformation();

    }

    @Override
    protected void onResume() {
        super.onResume();
        doOperations();
    }

    private void connectionInformation() {
        if (!system.isConnected())
            turnOnDataOrWifi();
    }

    private void turnOnDataOrWifi() {
        system.accessNetworkSettings(HomeActivity.this);
    }

    private void doOperations() {

        loadDataInBackground();
        setToolbar();
        GridView gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter( new GridAdapter(this, menus));

        hideProgressDialogue(mProgressDialog);
        showDataOnItemClick(gridView);
    }

    private void setToolbar() {
        TextView textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText(AppConsts.APP_TITLE);
    }

    private void hideProgressDialogue(ProgressDialog mProgressDialog) {
        mProgressDialog.cancel();
    }

    private void loadDataInBackground() {

        try {
            RequestMainMenu requestMenu = new RequestMainMenu(this);
            requestMenu.execute();
            menus = requestMenu.getMenus();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDataOnItemClick(GridView gridView) {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String menuId = getDataFromRequestedMenu(position);
                Intent intent = new Intent(HomeActivity.this, HelperActivity.class);
                intent.putExtra("MenuId", menuId);
                startActivity(intent);
            }
        });
    }

    private String getDataFromRequestedMenu(int position) {
        return menus.get(position).getMenuId().toString();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case AppConsts.REQUEST_CODE:{

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (permissionStatus.isLocationAccessAllowed(HomeActivity.this)){
                        return;
                    }
                    else Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
            return;
        }
        return;
    }
}

