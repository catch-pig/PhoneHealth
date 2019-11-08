package com.harman.phonehealth.base.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;

import com.harman.phonehealth.base.BaseContract;
import com.harman.phonehealth.base.BasePresenter;

import javax.inject.Inject;

/**
 * 创建时间:2019/4/6 11:25<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/4/6 11:25<br/>
 * 描述:
 */
public abstract class BasePresenterFragment<P extends BaseContract.Presenter> extends BaseFragment implements LifecycleObserver {
    @Inject
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initParam();
        injectComponent();
        getLifecycle().addObserver(mPresenter);
        initView();
    }

    protected abstract void initParam();

    protected abstract void injectComponent();

    protected abstract void initView();
}
