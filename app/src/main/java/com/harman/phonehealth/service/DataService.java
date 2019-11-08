package com.harman.phonehealth.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.base.BaseActivity;
import com.harman.phonehealth.di.module.DataModule;
import com.harman.phonehealth.service.presenter.DataPresenter;

import javax.inject.Inject;

public class DataService extends Service implements DataContract.View{
    @Inject
    DataContract.Presenter mPresenter;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PhoneHealthApp.getAppComponent().DataComponent(new DataModule(this)).inject(this);
        mPresenter.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {




        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public BaseActivity getBaseActivity() {
        return null;
    }

    @Override
    public void closeActivity() {

    }
}
