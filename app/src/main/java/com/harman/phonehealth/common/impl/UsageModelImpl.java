package com.harman.phonehealth.common.impl;

import android.app.Application;
import android.content.Context;

import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.entity.PackageInfoBean;
import com.harman.phonehealth.utils.UseTimeDataManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class UsageModelImpl implements UsageModel {

    private UseTimeDataManager useTimeDataManager;
    private Context mContext;

    public UsageModelImpl(Application application) {
        mContext = application;
        useTimeDataManager = UseTimeDataManager.getInstance(mContext);
    }

    @Override
    public List<PackageInfoBean> getTodayData() {
//        useTimeDataManager.refreshData(0);
        return useTimeDataManager.getmPackageInfoListOrderByTime();
    }

    @Override
    public List<PackageInfoBean> getSevenDayData() {
//        useTimeDataManager.refreshData(7);
        return useTimeDataManager.getmPackageInfoListOrderByTime();
    }

    @Override
    public List<PackageInfoBean> getOneDayData(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long startTime;
        try {
            startTime = dateFormat.parse(date).getTime();
            if (startTime > System.currentTimeMillis()){
                return null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        useTimeDataManager.refreshData(date);
        return useTimeDataManager.getmPackageInfoListOrderByTime();
    }
}
