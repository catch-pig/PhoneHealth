package com.harman.phonehealth.di.module;

import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.di.scope.ActicityScope;
import com.harman.phonehealth.mvp.main.MainContract;
import com.harman.phonehealth.mvp.main.adapter.MainAdapter;
import com.harman.phonehealth.mvp.main.model.MainModel;
import com.harman.phonehealth.mvp.main.presenter.MainPresenter;
import com.harman.phonehealth.mvp.statistics.StatisticsContract;
import com.harman.phonehealth.mvp.statistics.presenter.StatisticsPresenter;
import com.harman.phonehealth.mvp.statistics.view.StatisticsActivity;

import androidx.fragment.app.FragmentManager;
import dagger.Module;
import dagger.Provides;

@Module
public class StatisticsModule extends BaseActivityModule<StatisticsContract.View> {

    public StatisticsModule(StatisticsContract.View view) {
        super(view);
    }

    @ActicityScope
    @Provides
    public StatisticsContract.Presenter providesMainPresenter(UsageModel mainModel) {
        return new StatisticsPresenter(mView, mainModel);
    }

//    @ActicityScope
//    @Provides
//    public MainAdapter providesMainAdapter() {
//        return new MainAdapter(mFragmentManager);
//    }
}
