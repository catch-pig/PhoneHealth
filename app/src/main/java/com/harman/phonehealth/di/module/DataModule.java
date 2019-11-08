package com.harman.phonehealth.di.module;

import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.service.DataContract;
import com.harman.phonehealth.service.presenter.DataPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    private DataContract.View mView;
    public DataModule(DataContract.View view){
        mView = view;
    }
    @Provides
    public DataContract.Presenter providesDataPresenter(UsageModel usageModel){
        return new DataPresenter(mView,usageModel);
    }
}
