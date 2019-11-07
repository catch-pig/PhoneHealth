package com.harman.phonehealth.di.component;

import com.harman.phonehealth.di.module.AppModule;
import com.harman.phonehealth.di.module.MainModule;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    MainComponent mainComponent(MainModule mainModule);
}
