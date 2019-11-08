package com.harman.phonehealth.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.base.BaseActivity;
import com.harman.phonehealth.di.module.DataModule;
import com.harman.phonehealth.mvp.main.view.MainActivity;

import javax.inject.Inject;

public class DataService extends Service implements DataContract.View {
    public static final String ACTION_INSERT_SUCCESS_TO_DB = "insertSuccess";
    @Inject
    DataContract.Presenter mPresenter;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PhoneHealthApp.getAppComponent().dataComponent(new DataModule(this)).inject(this);
        mPresenter.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MainActivity.ACTION_USAGE_ACCESS);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mPresenter.insertData();
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void insertSuccessToDb() {
        Intent intent = new Intent(ACTION_INSERT_SUCCESS_TO_DB);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public BaseActivity getBaseActivity() {
        return null;
    }

    @Override
    public void closeActivity() {

    }

    @Override
    public void onDestroy() {
        if (mBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
        }
    }
}
