package com.altran.ibanarriola.teamworktest.injection;

import com.altran.ibanarriola.teamworktest.repository.ProjectRepository;
import com.altran.ibanarriola.teamworktest.repository.datasource.ApiDataSource;
import com.altran.ibanarriola.teamworktest.usecase.GetProjectList;
import com.altran.ibanarriola.teamworktest.view.activity.ProjectsListActivity;
import com.altran.ibanarriola.teamworktest.view.presenter.ProjectsListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProjectListModule {

    @Provides
    ProjectRepository providesProjectRepository(ApiDataSource apiDataSource){
        return new ProjectRepository(apiDataSource);
    }

    @Provides
    GetProjectList providesGetProjectsList(ProjectRepository projectRepository){
        return new GetProjectList(projectRepository);
    }

    @Provides
    ProjectsListPresenter.View provideProjectsListView(ProjectsListActivity projectsListActivity){
        return projectsListActivity;
    }

    @Provides @PerActivity
    ProjectsListPresenter providesProjectsListPresenter(GetProjectList getProjectList){
        return new ProjectsListPresenter(getProjectList);
    }
}
