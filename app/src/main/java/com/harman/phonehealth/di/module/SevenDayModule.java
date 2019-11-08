package com.harman.phonehealth.di.module;

import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.di.scope.FragmentScope;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;
import com.harman.phonehealth.mvp.sevenday.SevenDayContract;
import com.harman.phonehealth.mvp.sevenday.presenter.SevenDayPresenter;
import com.harman.phonehealth.mvp.today.TodayContract;
import com.harman.phonehealth.mvp.today.presenter.TodayPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SevenDayModule extends BaseFragmentModule<SevenDayContract.View> {
    public SevenDayModule(SevenDayContract.View view) {
        super(view);
    }
    @FragmentScope
    @Provides
    public SevenDayContract.Presenter providesTodatPresenter(UsageModel usageModel, AppInfoAdater appInfoAdater){
        return new SevenDayPresenter(mView,usageModel,appInfoAdater);
    }

    @FragmentScope
    @Provides
    public AppInfoAdater providesAppInfoAdapter(){
        return new AppInfoAdater();
    }
}
