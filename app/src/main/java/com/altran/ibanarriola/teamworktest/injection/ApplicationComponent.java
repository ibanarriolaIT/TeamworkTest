package com.altran.ibanarriola.teamworktest.injection;

import com.altran.ibanarriola.teamworktest.Application;
import com.altran.ibanarriola.teamworktest.repository.datasource.DataModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, InjectorBuilder.class, DataModule.class})
public interface ApplicationComponent extends AndroidInjector<Application> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(Application app);
}
