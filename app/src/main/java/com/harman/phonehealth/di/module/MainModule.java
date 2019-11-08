package com.harman.phonehealth.di.module;

import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.di.scope.ActicityScope;
import com.harman.phonehealth.mvp.main.MainContract;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;
import com.harman.phonehealth.mvp.main.model.MainModel;
import com.harman.phonehealth.mvp.main.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule extends BaseActivityModule<MainContract.View>{
    public MainModule(MainContract.View view) {
        super(view);
    }

    @ActicityScope
    @Provides
    public MainContract.Presenter providesMainPresenter(MainModel mainModel, UsageModel usageModel,AppInfoAdater appInfoAdater){
        return new MainPresenter(mView,mainModel,usageModel,appInfoAdater);
    }
    @ActicityScope
    @Provides
    public AppInfoAdater providesAppInfoAdapter(){
        return new AppInfoAdater();
    }
}
