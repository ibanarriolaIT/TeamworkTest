package com.altran.ibanarriola.teamworktest.view.mvvm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.altran.ibanarriola.teamworktest.usecase.GetProjectList;

public class ProjectsListViewModelFactory implements ViewModelProvider.Factory {

    GetProjectList getProjectList;

    public ProjectsListViewModelFactory(GetProjectList getProjectList) {
        this.getProjectList = getProjectList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProjectsListViewModel.class)) {
            return (T) new ProjectsListViewModel(getProjectList);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
