package com.harman.phonehealth.di.module;

import com.harman.phonehealth.base.BaseContract;
import com.harman.phonehealth.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * 创建时间:2019/4/6 12:52<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/4/6 12:52<br/>
 * 描述:
 */
@Module
public abstract class BaseFragmentModule<V extends BaseContract.View> {
    protected V mView;

    public BaseFragmentModule(V view) {
        mView = view;
    }
    @FragmentScope
    @Provides
    public V providesView(){
        return mView;
    }
}
