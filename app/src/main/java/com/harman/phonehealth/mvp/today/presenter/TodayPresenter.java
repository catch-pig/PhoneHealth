package com.harman.phonehealth.mvp.today.presenter;

import com.harman.phonehealth.base.BasePresenter;
import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.entity.PackageInfoBean;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;
import com.harman.phonehealth.mvp.today.TodayContract;

import java.util.List;

public class TodayPresenter extends BasePresenter<TodayContract.View> implements TodayContract.Presenter {
    private final UsageModel mUsageModel;
    private final AppInfoAdater mAppInfoAdater;
    public TodayPresenter(TodayContract.View view,UsageModel usageModel,AppInfoAdater appInfoAdater) {
        super(view);
        mUsageModel = usageModel;
        mAppInfoAdater = appInfoAdater;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mView.initAdapter(mAppInfoAdater);
        List<PackageInfoBean> packageInfoBeans = mUsageModel.getOneDayData("2019-11-8");
        mAppInfoAdater.set(packageInfoBeans);
    }
}
