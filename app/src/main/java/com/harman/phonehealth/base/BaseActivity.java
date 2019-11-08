package com.harman.phonehealth.base;

import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {
    private Unbinder mUnbinder;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        mUnbinder = ButterKnife.bind(this);
    }

    protected abstract int layoutId();

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public BaseActivity getBaseActivity() {
        return this;
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
