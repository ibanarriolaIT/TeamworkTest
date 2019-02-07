package com.altran.ibanarriola.teamworktest.view.mvvm;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.altran.ibanarriola.teamworktest.common.DataWrapper;
import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;
import com.altran.ibanarriola.teamworktest.usecase.GetProjectList;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ProjectsListViewModel extends ViewModel {

    GetProjectList getProjectList;
    MutableLiveData<DataWrapper<List<ProjectModel.MapProject>>> liveData = new MutableLiveData<>();

    public ProjectsListViewModel(GetProjectList getProjectList) {
        this.getProjectList = getProjectList;
    }

    public LiveData<DataWrapper<List<ProjectModel.MapProject>>> getLiveData() {
        return liveData;
    }

    public void getProjects() {
        getProjectList.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ProjectModel.ProjectList::getProjects)
                .toObservable().flatMapIterable(projects -> projects)
                .map(project -> project.convertToMapProject()).toList()
                .subscribe(projectsList -> liveData.setValue(new DataWrapper<>(projectsList)),
                        throwable -> liveData.setValue(new DataWrapper<>(throwable)));
    }

}
