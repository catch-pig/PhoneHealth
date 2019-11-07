package com.harman.phonehealth.app;

import android.app.Application;
import android.util.Log;

import com.harman.phonehealth.di.component.AppComponent;
import com.harman.phonehealth.di.component.DaggerAppComponent;
import com.harman.phonehealth.di.module.AppModule;
import com.harman.phonehealth.util.EnCodingUtils;

public class PhoneHealthApp extends Application {

    public static Application sApplication;
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        testDES();
    }

    public static AppComponent getAppComponent(){
        if (sAppComponent == null) {
            sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(sApplication)).build();
        }
        return sAppComponent;
    }

    public void testDES(){
        EnCodingUtils enCodingUtils = EnCodingUtils.getInstance(this);
        enCodingUtils.init(EnCodingUtils.DES_MODE);
        byte[] endata = enCodingUtils.DESEncode("hello world".getBytes());
        Log.i("PHoneHealthAPP",new String(endata));

        byte[] dedata = enCodingUtils.DESDecode(endata);

        Log.i("PHoneHealthAPP",new String(dedata));

    }
}
