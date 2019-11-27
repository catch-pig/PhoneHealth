package com.harman.phonehealth.mvp.statistics.view;

import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.base.BasePresenterActivity;
import com.harman.phonehealth.di.module.MainModule;
import com.harman.phonehealth.di.module.StatisticsModule;
import com.harman.phonehealth.mvp.statistics.StatisticsContract;

public class StatisticsActivity extends BasePresenterActivity<StatisticsContract.Presenter> implements StatisticsContract.View {
    @Override
    protected void initParam() {

    }

    @Override
    protected void injectComponent() {
        PhoneHealthApp.getAppComponent().statisticsComponent(new StatisticsModule(this)).inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int layoutId() {
        return 0;
    }

    @Override
    public void initStatisticsView() {

    }
}
