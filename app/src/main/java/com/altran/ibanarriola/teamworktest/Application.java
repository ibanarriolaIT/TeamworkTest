package com.altran.ibanarriola.teamworktest;

import com.altran.ibanarriola.teamworktest.injection.ApplicationComponent;
import com.altran.ibanarriola.teamworktest.injection.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class Application extends DaggerApplication {

    protected ApplicationComponent applicationComponent;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        applicationComponent = DaggerApplicationComponent.builder().application(this).build();
        applicationComponent.inject(this);
        return applicationComponent;
    }

    public ApplicationComponent getAplicationComponent() {
        return applicationComponent;
    }
}

