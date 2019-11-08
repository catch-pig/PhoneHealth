package com.harman.phonehealth.di.module;

import com.harman.phonehealth.base.BaseContract;
import com.harman.phonehealth.di.scope.ActicityScope;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class BaseActivityModule<V extends BaseContract.View> {
    protected V mView;

    public BaseActivityModule(V view) {
        mView = view;
    }

    @ActicityScope
    @Provides
    public V providesView() {
        return mView;
    }
}
