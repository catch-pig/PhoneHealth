package com.harman.phonehealth.app;

import android.app.Application;
import android.content.Intent;

import com.harman.phonehealth.di.component.AppComponent;
import com.harman.phonehealth.di.component.DaggerAppComponent;
import com.harman.phonehealth.di.module.AppModule;
import com.harman.phonehealth.service.DataService;

public class PhoneHealthApp extends Application {

    public static Application sApplication;
    private static AppComponent sAppComponent;

    public static AppComponent getAppComponent() {
        if (sAppComponent == null) {
            sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(sApplication)).build();
        }
        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        Intent intent = new Intent(this, DataService.class);
        startService(intent);
//        testDES();
    }

    public void testDES() {
//        EnCodingUtils enCodingUtils = EnCodingUtils.getInstance(this);
//        enCodingUtils.init(EnCodingUtils.DES_MODE);
//        byte[] endata = enCodingUtils.DESEncode("hello world".getBytes());
//        Log.i("PHoneHealthAPP",new String(endata));
//
//        byte[] dedata = enCodingUtils.DESDecode(endata);
//
//        Log.i("PHoneHealthAPP",new String(dedata));

    }
}
