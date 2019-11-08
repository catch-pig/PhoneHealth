package com.harman.phonehealth.mvp.sevenday.presenter;

import com.harman.phonehealth.base.BasePresenter;
import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.entity.PackageInfoBean;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;
import com.harman.phonehealth.mvp.sevenday.SevenDayContract;

import java.util.List;

public class SevenDayPresenter extends BasePresenter<SevenDayContract.View> implements SevenDayContract.Presenter {

    private final UsageModel mUsageModel;
    private final AppInfoAdater mAppInfoAdater;
    public SevenDayPresenter(SevenDayContract.View view,UsageModel usageModel,AppInfoAdater appInfoAdater) {
        super(view);
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
