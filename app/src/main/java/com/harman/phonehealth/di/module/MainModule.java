package com.harman.phonehealth.di.module;

import androidx.fragment.app.FragmentManager;

import com.harman.phonehealth.di.scope.ActicityScope;
import com.harman.phonehealth.mvp.main.MainContract;
import com.harman.phonehealth.mvp.main.adapter.MainAdapter;
import com.harman.phonehealth.mvp.main.model.MainModel;
import com.harman.phonehealth.mvp.main.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule extends BaseActivityModule<MainContract.View> {
    private final FragmentManager mFragmentManager;

    public MainModule(MainContract.View view, FragmentManager fragmentManager) {
        super(view);
        mFragmentManager = fragmentManager;
    }

    @ActicityScope
    @Provides
    public MainContract.Presenter providesMainPresenter(MainModel mainModel, MainAdapter mainAdapter) {
        return new MainPresenter(mView, mainModel, mainAdapter);
    }

    @ActicityScope
    @Provides
    public MainAdapter providesMainAdapter() {
        return new MainAdapter(mFragmentManager);
    }
}
