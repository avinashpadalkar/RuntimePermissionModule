package com.mdindia.runtimepermissionmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnRequestComboPermission, mBtnRequestSinglePermission, btnAddFragment;
    private Context mContext;
    private RuntimePermissionUtils runtimePermissionUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        initializeComponents();
        setListeners();
    }

    private void setListeners() {
        mBtnRequestComboPermission.setOnClickListener(this);
        mBtnRequestSinglePermission.setOnClickListener(this);
        btnAddFragment.setOnClickListener(this);
    }

    private void initializeComponents() {
        mBtnRequestComboPermission = (Button) findViewById(R.id.btnRequestMultiplePermission);
        mBtnRequestSinglePermission = (Button) findViewById(R.id.btnRequestSinglePermission);
        btnAddFragment = (Button) findViewById(R.id.btnAddFragment);

        runtimePermissionUtils = new RuntimePermissionUtils();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRequestSinglePermission:
                if (RuntimePermissionUtils.checkAndroidVersionMarshmallowOrGreater()) {
                    if(runtimePermissionUtils.checkPermissionIsGranted(MainActivity.this, RuntimePermissionUtils.STORAGE)){
                        // Permission is Granted.
                        Toast.makeText(mContext, "Permission is Granted", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(mContext, RuntimePermissionUtils.class);
                        intent.putExtra("permission", RuntimePermissionUtils.STORAGE);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(mContext, "Your Phone is below Android M", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnRequestMultiplePermission:
                if (RuntimePermissionUtils.checkAndroidVersionMarshmallowOrGreater()) {
                    if(runtimePermissionUtils.checkPermissionIsGranted(MainActivity.this, RuntimePermissionUtils.MULTIPLE_PERMISSION)){
                        // Permission is Granted.
                        Toast.makeText(mContext, "Permission is Granted", Toast.LENGTH_SHORT).show();

                    }else {
                        Intent intent = new Intent(mContext, RuntimePermissionUtils.class);
                        intent.putExtra("permission", RuntimePermissionUtils.MULTIPLE_PERMISSION);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(mContext, "Your Phone is below Android M", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnAddFragment:
                getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.container, new DummyFragment()).commit();
                break;
        }
    }

}
