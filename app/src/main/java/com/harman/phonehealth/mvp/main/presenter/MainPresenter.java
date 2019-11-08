package com.harman.phonehealth.mvp.main.presenter;

import com.harman.phonehealth.base.BasePresenter;
import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.entity.PackageInfoBean;
import com.harman.phonehealth.mvp.main.MainContract;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;
import com.harman.phonehealth.mvp.main.adapter.MainAdapter;

import java.util.List;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    private final MainContract.Model mModel;
    private final MainAdapter mMainAdapter;

    public MainPresenter(MainContract.View view,MainContract.Model model,MainAdapter mainAdapter) {
        super(view);
        mModel = model;
        mMainAdapter = mainAdapter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mView.initAdapter(mMainAdapter);
    }
}
