package com.harman.phonehealth.di.component;

import com.harman.phonehealth.di.module.AppModule;
import com.harman.phonehealth.di.module.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    MainComponent mainComponent(MainModule mainModule);
}
