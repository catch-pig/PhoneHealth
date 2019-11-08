package com.harman.phonehealth.mvp.main.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.harman.phonehealth.R;
import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.base.BasePresenterActivity;
import com.harman.phonehealth.di.module.MainModule;
import com.harman.phonehealth.mvp.main.MainContract;
import com.harman.phonehealth.mvp.main.adapter.MainAdapter;
import com.harman.phonehealth.utils.PermissionUtils;

import butterknife.BindView;

public class MainActivity extends BasePresenterActivity<MainContract.Presenter> implements MainContract.View {
    public static final String ACTION_USAGE_ACCESS = "accessUsage";
    public static final int REQUEST_USAGE_ACCESS = 100;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private AlertDialog.Builder builder;

    @Override
    protected void initParam() {

    }

    @Override
    protected void injectComponent() {
        PhoneHealthApp.getAppComponent().mainComponent(new MainModule(this, getSupportFragmentManager())).inject(this);
    }

    @Override
    protected void initView() {
        if (!PermissionUtils.checkUsagePermission(this)) {
            builder = new AlertDialog.Builder(this).setTitle("Request Permission")
                    .setMessage("The application needs Usage State Permission").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                            startActivityForResult(intent, REQUEST_USAGE_ACCESS);
                        }
                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "Please reopen the application to request permission", Toast.LENGTH_LONG).show();
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
            builder.create().show();
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initAdapter(MainAdapter mainAdapter) {
        mViewPager.setAdapter(mainAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_USAGE_ACCESS:
                if (!PermissionUtils.checkUsagePermission(this)) {
                    Toast.makeText(this, "Please reopen the application to request permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

