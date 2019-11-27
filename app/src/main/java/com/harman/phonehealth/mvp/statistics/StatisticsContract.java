package com.harman.phonehealth.mvp.statistics;

import com.harman.phonehealth.base.BaseContract;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;

public interface StatisticsContract {
    interface View extends BaseContract.View {
        void initStatisticsView();
    }

    interface Presenter extends BaseContract.Presenter {
        void loadData();
    }

    interface Model {

    }
}
