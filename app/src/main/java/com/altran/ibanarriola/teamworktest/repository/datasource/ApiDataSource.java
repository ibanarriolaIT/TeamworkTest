package com.altran.ibanarriola.teamworktest.repository.datasource;


import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ApiDataSource {

    @Headers({
            "Content-Type: application/json"
    })
    @GET("projects.json")
    Single<ProjectModel.ProjectList> getProjects(@Header("Authorization") String encodeToken);

}
