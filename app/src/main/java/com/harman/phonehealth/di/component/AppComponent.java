package com.harman.phonehealth.di.component;

import com.harman.phonehealth.di.module.AppModule;
import com.harman.phonehealth.di.module.DataModule;
import com.harman.phonehealth.di.module.MainModule;
import com.harman.phonehealth.di.module.SevenDayModule;
import com.harman.phonehealth.di.module.StatisticsModule;
import com.harman.phonehealth.di.module.TodayModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    MainComponent mainComponent(MainModule mainModule);

    DataComponent dataComponent(DataModule dataModule);

    TodayComponent todayComponent(TodayModule todayModule);

    SevenDayComponent sevenDayComponent(SevenDayModule sevenDayModule);

    StatisticsComponent statisticsComponent(StatisticsModule statisticsModule);
}
