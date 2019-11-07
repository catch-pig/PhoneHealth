package com.harman.phonehealth.di.module;

import com.harman.phonehealth.di.scope.ActicityScope;
import com.harman.phonehealth.mvp.main.MainContract;
import com.harman.phonehealth.mvp.main.model.MainModel;
import com.harman.phonehealth.mvp.main.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule extends BaseActivityModule<MainContract.View>{
    public MainModule(MainContract.View view) {
        super(view);
    }

    @ActicityScope
    @Provides
    public MainContract.Presenter providesMainPresenter(MainModel mainModel){
        return new MainPresenter(mView,mainModel);
    }
}
