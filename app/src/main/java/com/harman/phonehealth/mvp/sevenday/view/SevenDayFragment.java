package com.harman.phonehealth.mvp.sevenday.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.harman.phonehealth.R;
import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.base.adapter.RecyclerAdapter;
import com.harman.phonehealth.base.fragment.BasePresenterFragment;
import com.harman.phonehealth.di.module.SevenDayModule;
import com.harman.phonehealth.entity.PackageInfoBean;
import com.harman.phonehealth.mvp.main.adapter.AppInfoAdater;
import com.harman.phonehealth.mvp.sevenday.SevenDayContract;
import com.harman.phonehealth.mvp.statistics.view.StatisticsActivity;
import com.harman.phonehealth.service.DataService;

import butterknife.BindView;

public class SevenDayFragment extends BasePresenterFragment<SevenDayContract.Presenter> implements SevenDayContract.View {
    @BindView(R.id.recycle)
    RecyclerView mRecyclerView;
    private BroadcastReceiver mBroadcastReceiver;

    public static SevenDayFragment getInstance() {
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
        mPresenter.loadData();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DataService.ACTION_INSERT_SUCCESS_TO_DB);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mPresenter.loadData();
            }
        };
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_app_info;
    }

    @Override
    public void initAdapter(final AppInfoAdater appInfoAdater) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(appInfoAdater);
        appInfoAdater.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int id, Object o, int position) {
                PackageInfoBean data = appInfoAdater.getData().get(position);
                String appName = data.getPackageName();
                Intent intent = new Intent(getContext(), StatisticsActivity.class);
                intent.putExtra("appName",appName);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiver);
        }
    }
}
