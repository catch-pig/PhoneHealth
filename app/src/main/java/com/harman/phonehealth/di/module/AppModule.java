package com.harman.phonehealth.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application mApplication;
    public AppModule(Application application){
        mApplication = application;
    }
    @Singleton
    @Provides
    public Application providesApplication(){
        return mApplication;
    }
}
