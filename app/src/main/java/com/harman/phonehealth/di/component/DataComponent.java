package com.harman.phonehealth.di.component;

import com.harman.phonehealth.di.module.DataModule;
import com.harman.phonehealth.service.DataService;

import dagger.Subcomponent;

@Subcomponent(modules = DataModule.class)
public interface DataComponent {
    void inject(DataService dataService);
}
