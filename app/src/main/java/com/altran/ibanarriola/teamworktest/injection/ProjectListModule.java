package com.altran.ibanarriola.teamworktest.injection;

import com.altran.ibanarriola.teamworktest.repository.ProjectRepository;
import com.altran.ibanarriola.teamworktest.repository.datasource.ApiDataSource;
import com.altran.ibanarriola.teamworktest.usecase.GetProjectList;
import com.altran.ibanarriola.teamworktest.view.mvvm.ProjectsListViewModelFactory;

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

    @Provides @PerActivity
    ProjectsListViewModelFactory providesProjectsListViewModelFactory(GetProjectList getProjectList){
        return new ProjectsListViewModelFactory(getProjectList);
    }
}
