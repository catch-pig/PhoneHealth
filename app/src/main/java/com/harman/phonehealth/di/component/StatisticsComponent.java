package com.harman.phonehealth.di.component;

import com.harman.phonehealth.di.module.StatisticsModule;
import com.harman.phonehealth.di.scope.ActicityScope;
import com.harman.phonehealth.mvp.statistics.view.StatisticsActivity;

import dagger.Subcomponent;

@ActicityScope
@Subcomponent(modules = StatisticsModule.class)
public interface StatisticsComponent {
    void inject(StatisticsActivity mainActivity);
}
