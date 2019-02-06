package com.altran.ibanarriola.teamworktest.usecase;

import com.altran.ibanarriola.teamworktest.repository.ProjectRepository;
import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;

import io.reactivex.Single;

public class GetProjectList {

    private final ProjectRepository projectRepository;

    public GetProjectList(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Single<ProjectModel.ProjectList> execute(){
        return Single.create(emitter -> projectRepository.getProjects()
                .subscribe(
                        emitter::onSuccess,
                        throwable -> {
                            if (!emitter.isDisposed()) {
                                emitter.onError(throwable);
                            }
                        }));
    }
}
