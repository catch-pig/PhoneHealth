package com.harman.phonehealth.common;


import com.harman.phonehealth.entity.PackageInfoBean;

import java.util.List;

public interface UsageModel {

    List<PackageInfoBean> getTodayData();

    List<PackageInfoBean> getSevenDayData();

    List<PackageInfoBean> getOneDayData(String date) throws Exception;

}
