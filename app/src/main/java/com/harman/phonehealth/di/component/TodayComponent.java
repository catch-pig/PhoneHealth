package com.harman.phonehealth.di.component;

import com.harman.phonehealth.di.module.TodayModule;
import com.harman.phonehealth.di.scope.FragmentScope;
import com.harman.phonehealth.mvp.today.view.TodayFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = TodayModule.class)
public interface TodayComponent {
    void inject(TodayFragment todayFragment);
}
