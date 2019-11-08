package com.harman.phonehealth.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.harman.phonehealth.base.BaseActivity;
import com.harman.phonehealth.base.BaseContract;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 创建时间:2019/4/4 23:14<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/4/4 23:14<br/>
 * 描述:
 */
public abstract class BaseFragment extends Fragment implements BaseContract.View {
    private Unbinder mUnbinder;
    private BaseActivity mBaseActivity;

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @LayoutRes
    protected abstract int layoutId();


    @Override
    public BaseActivity getBaseActivity() {
        if (mBaseActivity == null) {
            Activity activity = getActivity();
            if (activity instanceof BaseActivity) {
                mBaseActivity = (BaseActivity) activity;
            }
        }
        return mBaseActivity;
    }

    @Override
    public void closeActivity() {
        getBaseActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
