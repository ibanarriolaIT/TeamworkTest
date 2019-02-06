package com.altran.ibanarriola.teamworktest.injection;

import com.altran.ibanarriola.teamworktest.view.activity.ProjectsListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InjectorBuilder {

    @ContributesAndroidInjector(modules = ProjectListModule.class)
    @PerActivity
    abstract ProjectsListActivity contributeGnomeListActivity();
}
