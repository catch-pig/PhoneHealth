package com.harman.phonehealth.mvp.sevenday.presenter;

import com.harman.phonehealth.base.BasePresenter;
import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.entity.PackageInfoBean;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;
import com.harman.phonehealth.mvp.sevenday.SevenDayContract;
import com.harman.phonehealth.util.RoomUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
    }

    @Override
    public void loadData() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        long endTime = 0;
        try {
            endTime = dateFormat.parse(year+"-"+month+"-"+day).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long startTime = endTime - (1000*60*60*24*6);
        RoomUtils.getDataBase(mView.getBaseActivity())
                .packageInfoBeanDao()
                .findPackageInfoSomeAppSomeDayByUsedTimeAsc(startTime,endTime)
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
