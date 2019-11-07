package com.harman.phonehealth.di.module;

import android.app.Application;

import androidx.room.Room;

import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.common.impl.UsageModelImpl;
import com.harman.phonehealth.database.AppDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application mApplication;
    private AppDataBase mAppDataBase;
    public AppModule(Application application){
        mApplication = application;
        mAppDataBase = Room.databaseBuilder(application.getApplicationContext(),AppDataBase.class,"database-phone-health").build();
    }
    @Singleton
    @Provides
    public Application providesApplication(){
        return mApplication;
    }

    @Singleton
    @Provides
    public UsageModel providesUsageModel(){
        return new UsageModelImpl(mApplication);
    }

    @Singleton
    @Provides
    public AppDataBase providesAppDataBase(){
        return mAppDataBase;
    }
}
