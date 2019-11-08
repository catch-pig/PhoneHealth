package com.harman.phonehealth.mvp.today.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.harman.phonehealth.R;
import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.base.fragment.BaseFragment;
import com.harman.phonehealth.base.fragment.BasePresenterFragment;
import com.harman.phonehealth.di.module.TodayModule;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;
import com.harman.phonehealth.mvp.today.TodayContract;

import butterknife.BindView;

public class TodayFragment extends BasePresenterFragment<TodayContract.Presenter> implements TodayContract.View {
    @BindView(R.id.recycle)
    RecyclerView mRecyclerView;

    public static TodayFragment getInstance(){
        return new TodayFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_app_info;
    }

    @Override
    protected void initParam() {

    }

    @Override
    protected void injectComponent() {
        PhoneHealthApp.getAppComponent().todayComponent(new TodayModule(this)).inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initAdapter(AppInfoAdater appInfoAdater) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(appInfoAdater);
    }
}
