package com.harman.phonehealth.mvp.main.presenter;

import com.harman.phonehealth.base.BasePresenter;
import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.entity.PackageInfoBean;
import com.harman.phonehealth.mvp.main.MainContract;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;

import java.util.List;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    private final MainContract.Model mModel;
    private final UsageModel mUsageModel;
    private final AppInfoAdater mAppInfoAdater;

    public MainPresenter(MainContract.View view,MainContract.Model model,UsageModel usageModel,AppInfoAdater appInfoAdater) {
        super(view);
        mModel = model;
        mUsageModel = usageModel;
        mAppInfoAdater = appInfoAdater;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mView.initAdapter(mAppInfoAdater);
        List<PackageInfoBean> packageInfoBeans = mUsageModel.getOneDayData("2019-11-7");
        mAppInfoAdater.set(packageInfoBeans);
    }
}
