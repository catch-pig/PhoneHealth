package com.harman.phonehealth.service;

import com.harman.phonehealth.base.BaseContract;

public interface DataContract {
    interface View extends BaseContract.View{
        void insertSuccessToDb();
    }
    interface Presenter extends BaseContract.Presenter{
        void insertData();
    }
}
