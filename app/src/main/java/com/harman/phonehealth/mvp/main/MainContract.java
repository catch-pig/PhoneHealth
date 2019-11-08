package com.harman.phonehealth.mvp.main;

import com.harman.phonehealth.base.BaseContract;
import com.harman.phonehealth.mvp.main.adapter.MainAdapter;

public interface MainContract {
    interface View extends BaseContract.View {
        void initAdapter(MainAdapter mainAdapter);
    }

    interface Presenter extends BaseContract.Presenter {

    }

    interface Model {

    }
}
