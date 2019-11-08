package com.harman.phonehealth.di.component;

import com.harman.phonehealth.di.module.SevenDayModule;
import com.harman.phonehealth.di.module.TodayModule;
import com.harman.phonehealth.di.scope.FragmentScope;
import com.harman.phonehealth.mvp.sevenday.view.SevenDayFragment;
import com.harman.phonehealth.mvp.today.view.TodayFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = SevenDayModule.class)
public interface SevenDayComponent {
    void inject(SevenDayFragment sevenDayFragment);
}
