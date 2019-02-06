package com.altran.ibanarriola.teamworktest.repository;

import com.altran.ibanarriola.teamworktest.repository.datasource.ApiDataSource;
import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;
import com.altran.ibanarriola.teamworktest.utils.ExtensionFunctionsKt;

import io.reactivex.Single;

public class ProjectRepository {

    private static final String TOKEN = "twp_ocsj8PR64FIV48fHVXCy75gBruca";

    private ApiDataSource apiDataSource;

    public ProjectRepository(ApiDataSource apiDataSource) {
        this.apiDataSource = apiDataSource;
    }

    public Single<ProjectModel.ProjectList> getProjects() {
        return apiDataSource.getProjects(ExtensionFunctionsKt.generateEncodedToken(TOKEN))
                .onErrorResumeNext(Single::error);
    }
}
