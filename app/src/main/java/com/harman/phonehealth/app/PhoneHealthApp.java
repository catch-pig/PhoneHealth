package com.harman.phonehealth.app;

import android.app.Application;

import com.harman.phonehealth.di.component.AppComponent;
import com.harman.phonehealth.di.component.DaggerAppComponent;
import com.harman.phonehealth.di.module.AppModule;

public class PhoneHealthApp extends Application {

    public static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static AppComponent getAppComponent(){
        return DaggerAppComponent.builder().appModule(new AppModule(sApplication)).build();
    }
}
