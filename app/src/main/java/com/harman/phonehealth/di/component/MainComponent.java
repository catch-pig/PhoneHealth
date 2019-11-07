package com.harman.phonehealth.di.component;

import com.harman.phonehealth.di.module.MainModule;
import com.harman.phonehealth.di.scope.ActicityScope;
import com.harman.phonehealth.mvp.main.view.MainActivity;

import dagger.Subcomponent;

@ActicityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
