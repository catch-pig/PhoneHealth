package com.harman.phonehealth.mvp.statistics.presenter;

import com.harman.phonehealth.base.BasePresenter;
import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.entity.PackageInfoBean;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;
import com.harman.phonehealth.mvp.statistics.StatisticsContract;
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

public class StatisticsPresenter extends BasePresenter<StatisticsContract.View> implements StatisticsContract.Presenter {
    private final UsageModel mUsageModel;

    public StatisticsPresenter(StatisticsContract.View view, UsageModel usageModel) {
        super(view);
        mUsageModel = usageModel;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mView.initStatisticsView();

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

                    }
                });
    }
}
