package com.harman.phonehealth.mvp.main.presenter;

import android.util.Log;

import com.harman.phonehealth.base.BasePresenter;
import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.mvp.main.MainContract;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    private final MainContract.Model mModel;
    private final UsageModel mUsageModel;

    public MainPresenter(MainContract.View view,MainContract.Model model,UsageModel usageModel) {
        super(view);
        mModel = model;
        mUsageModel = usageModel;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("testa",mUsageModel.getOneDayData("2019-11-06").toString());
    }
}
