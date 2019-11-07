package com.harman.phonehealth.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import javax.inject.Inject;

public abstract class BasePresenterActivity<P extends BaseContract.Presenter> extends BaseActivity {
    @Inject
    P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent();
        initParam();
        getLifecycle().addObserver(mPresenter);
        initView();
    }

    protected abstract void initParam();
    protected abstract void injectComponent();
    protected abstract void initView();
}
