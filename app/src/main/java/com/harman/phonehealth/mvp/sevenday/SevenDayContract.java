package com.harman.phonehealth.mvp.sevenday;

import com.harman.phonehealth.base.BaseContract;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;

public interface SevenDayContract {
    interface View extends BaseContract.View{
        void initAdapter(AppInfoAdater appInfoAdater);
    }
    interface Presenter extends BaseContract.Presenter{

    }
    interface Model{

    }
}
