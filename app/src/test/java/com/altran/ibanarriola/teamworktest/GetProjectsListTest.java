package com.altran.ibanarriola.teamworktest;

import com.altran.ibanarriola.teamworktest.repository.ProjectRepository;
import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;
import com.altran.ibanarriola.teamworktest.usecase.GetProjectList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.PublishSubject;

import static org.mockito.Mockito.when;

public class GetProjectsListTest {

    ProjectRepository projectRepository = Mockito.mock(ProjectRepository.class);
    ProjectModel.Project project = new ProjectModel.Project("testing",
            "this is a test",
            "https://logo.jpg",
            new ProjectModel.Company("cat"),
            134823483,
            "active");
    List<ProjectModel.Project> projects = Arrays.asList(project);
    ProjectModel.ProjectList projectsList = new ProjectModel.ProjectList(projects);

    @InjectMocks
    GetProjectList getProjectList;

    private PublishSubject<ProjectModel.ProjectList> projectsListPublishSubject = PublishSubject.create();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(projectRepository.getProjects()).thenReturn(projectsListPublishSubject.take(1).singleOrError());
    }

    @Test
    public void testExecuteGetProjectsListSuccess(){
        TestObserver testObserver = getProjectList.execute().test();
        projectsListPublishSubject.onNext(projectsList);
        testObserver.assertComplete();
    }

    @Test
    public void testExecuteGetProjectsListError(){
        Throwable throwable = new Throwable();
        TestObserver testObserver = getProjectList.execute().test();
        projectsListPublishSubject.onError(throwable);
        testObserver.assertError(throwable);
    }

}