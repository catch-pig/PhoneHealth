package com.harman.phonehealth.di.component;

import com.harman.phonehealth.di.module.AppModule;
import com.harman.phonehealth.di.module.MainModule;
import com.harman.phonehealth.di.module.SevenDayModule;
import com.harman.phonehealth.di.module.TodayModule;
import com.harman.phonehealth.mvp.today.TodayContract;
import com.harman.phonehealth.mvp.today.view.TodayFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    MainComponent mainComponent(MainModule mainModule);

    TodayComponent todayComponent(TodayModule todayModule);

    SevenDayComponent sevenDayComponent(SevenDayModule sevenDayModule);
}
