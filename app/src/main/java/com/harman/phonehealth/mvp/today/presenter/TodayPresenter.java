package com.harman.phonehealth.mvp.today.presenter;

import com.harman.phonehealth.base.BasePresenter;
import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.entity.PackageInfoBean;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;
import com.harman.phonehealth.mvp.today.TodayContract;
import com.harman.phonehealth.util.RoomUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TodayPresenter extends BasePresenter<TodayContract.View> implements TodayContract.Presenter {
    private final UsageModel mUsageModel;
    private final AppInfoAdater mAppInfoAdater;

    public TodayPresenter(TodayContract.View view, UsageModel usageModel, AppInfoAdater appInfoAdater) {
        super(view);
        mUsageModel = usageModel;
        mAppInfoAdater = appInfoAdater;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mView.initAdapter(mAppInfoAdater);

    }

    @Override
    public void loadData() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long today = 0;
        try {
            today = dateFormat.parse(dateFormat.format(new Date())).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RoomUtils.getDataBase(mView.getBaseActivity())
                .packageInfoBeanDao()
                .findPackageInfoSomeDayByUsedTimeDesc(today)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PackageInfoBean>>() {
                    @Override
                    public void accept(List<PackageInfoBean> packageInfoBeans) throws Exception {
                        mAppInfoAdater.set(packageInfoBeans);
                    }
                });
    }
}
