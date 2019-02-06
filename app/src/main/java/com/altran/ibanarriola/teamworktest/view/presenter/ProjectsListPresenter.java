package com.altran.ibanarriola.teamworktest.view.presenter;

import com.altran.ibanarriola.teamworktest.common.BasePresenter;
import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;
import com.altran.ibanarriola.teamworktest.usecase.GetProjectList;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProjectsListPresenter extends BasePresenter{

    GetProjectList getProjectList;

    public ProjectsListPresenter(GetProjectList getProjectList){
        this.getProjectList = getProjectList;
    }

    public void getProjects(){
        ((View) getView()).onLoading();
        getProjectList.execute()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .map(ProjectModel.ProjectList::getProjects)
                .toObservable().flatMapIterable(projects -> projects)
                .map(project -> project.convertToMapProject()).toList()
        .subscribe(projectsList ->
            ((View) getView()).onProjectsDataReceived(projectsList),
                throwable -> ((View) getView()).onErrorReceivingProjects());
    }

    public interface View {
        void onLoading();
        void onProjectsDataReceived(List<ProjectModel.MapProject> projects);
        void onErrorReceivingProjects();
    }
}
