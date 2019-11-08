package com.harman.phonehealth.mvp.main.view;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.harman.phonehealth.R;
import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.base.BasePresenterActivity;
import com.harman.phonehealth.di.module.MainModule;
import com.harman.phonehealth.mvp.main.MainContract;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;
import com.harman.phonehealth.utils.PermissionUtils;

import butterknife.BindView;

public class MainActivity extends BasePresenterActivity<MainContract.Presenter> implements MainContract.View {

    @BindView(R.id.recycle)
    RecyclerView mRecyclerView;

    @Override
    protected void initParam() {

    }

    @Override
    protected void injectComponent() {
        PhoneHealthApp.getAppComponent().mainComponent(new MainModule(this)).inject(this);
    }

    @Override
    protected void initView() {
        if (!PermissionUtils.checkUsagePermission(this)) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivityForResult(intent,1);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initAdapter(AppInfoAdater appInfoAdater) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(appInfoAdater);
    }
}

