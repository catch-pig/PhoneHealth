package com.harman.phonehealth.mvp.today;

import com.harman.phonehealth.base.BaseContract;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;

public interface TodayContract {
    interface View extends BaseContract.View{
        void initAdapter(AppInfoAdater appInfoAdater);
    }
    interface Presenter extends BaseContract.Presenter{

    }
    interface Model{

    }
}
