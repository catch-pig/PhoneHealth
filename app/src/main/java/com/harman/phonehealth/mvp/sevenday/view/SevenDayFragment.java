package com.harman.phonehealth.mvp.sevenday.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.harman.phonehealth.R;
import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.base.fragment.BasePresenterFragment;
import com.harman.phonehealth.di.module.SevenDayModule;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;
import com.harman.phonehealth.mvp.sevenday.SevenDayContract;

import butterknife.BindView;

public class SevenDayFragment extends BasePresenterFragment<SevenDayContract.Presenter> implements SevenDayContract.View {
    @BindView(R.id.recycle)
    RecyclerView mRecyclerView;
    public static SevenDayFragment getInstance(){
        return new SevenDayFragment();
    }
    @Override
    protected void initParam() {

    }

    @Override
    protected void injectComponent() {
        PhoneHealthApp.getAppComponent().sevenDayComponent(new SevenDayModule(this)).inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_app_info;
    }
    @Override
    public void initAdapter(AppInfoAdater appInfoAdater) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(appInfoAdater);
    }
}
