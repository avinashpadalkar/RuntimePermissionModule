package com.mdindia.runtimepermissionmodule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;



public class DummyFragment extends Fragment implements View.OnClickListener {

    private Button btnRequestMultiplePermission, btnRequestSinglePermission;
    private View myView;
    private Activity mActivity;
    private Context mContext;
    private RuntimePermissionUtils runtimePermissionUtils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.layout_dummy_fragment, container, false);

        initializeComponents();
        setClickListener();

        return myView;
    }

    private void setClickListener() {
        btnRequestMultiplePermission.setOnClickListener(this);
        btnRequestSinglePermission.setOnClickListener(this);
    }

    private void initializeComponents() {
        mActivity = getActivity();
        mContext = getActivity();
        runtimePermissionUtils = new RuntimePermissionUtils();
        btnRequestMultiplePermission = (Button) myView.findViewById(R.id.btnRequestMultiplePermission);
        btnRequestSinglePermission = (Button) myView.findViewById(R.id.btnRequestSinglePermission);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRequestSinglePermission:
                if (RuntimePermissionUtils.checkAndroidVersionMarshmallowOrGreater()) {
                    if (runtimePermissionUtils.checkPermissionIsGranted(mActivity, RuntimePermissionUtils.STORAGE)) {

                        // Permission is Granted.
                        Toast.makeText(mContext, "Permission is Granted", Toast.LENGTH_SHORT).show();

                    } else {
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
                    if (runtimePermissionUtils.checkPermissionIsGranted(mActivity, RuntimePermissionUtils.MULTIPLE_PERMISSION)) {

                        // Permission is Granted.
                        Toast.makeText(mContext, "Permission is Granted", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(mContext, RuntimePermissionUtils.class);
                        intent.putExtra("permission", RuntimePermissionUtils.MULTIPLE_PERMISSION);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(mContext, "Your Phone is below Android M", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }
}
