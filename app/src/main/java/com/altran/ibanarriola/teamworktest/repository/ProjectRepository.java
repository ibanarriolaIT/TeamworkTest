package com.altran.ibanarriola.teamworktest.repository;

import android.util.Base64;

import com.altran.ibanarriola.teamworktest.repository.datasource.ApiDataSource;
import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import io.reactivex.Single;

public class ProjectRepository {

    private static final String TOKEN = "twp_ocsj8PR64FIV48fHVXCy75gBruca";
    private static final char[] map1 = new char[64];

    ApiDataSource apiDataSource;

    public ProjectRepository(ApiDataSource apiDataSource) {
        this.apiDataSource = apiDataSource;
    }

    public Single<ProjectModel.ProjectList> getProjects() {
        return apiDataSource.getProjects(encodeToken())
                .onErrorResumeNext(throwable -> Single.error(throwable));
    }

    private String encodeToken() {
        String userPassword = TOKEN + ":";
        String encodedAuthorization = encodeString(userPassword);
        encodedAuthorization = "Basic " + encodedAuthorization;
        return encodedAuthorization;
    }

    private String encodeString(String s) {
        return Base64.encodeToString(s.getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP | Base64.URL_SAFE);
    }
}
