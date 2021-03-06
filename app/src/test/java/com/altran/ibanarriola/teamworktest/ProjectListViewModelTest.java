package com.altran.ibanarriola.teamworktest;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;

import com.altran.ibanarriola.teamworktest.common.DataWrapper;
import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;
import com.altran.ibanarriola.teamworktest.usecase.GetProjectList;
import com.altran.ibanarriola.teamworktest.view.mvvm.ProjectsListViewModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subjects.PublishSubject;
import kotlin.jvm.Throws;

import static org.mockito.Mockito.when;

public class ProjectListViewModelTest {

    private GetProjectList getProjectList = Mockito.mock(GetProjectList.class);
    private ProjectModel.Project project = new ProjectModel.Project("testing",
            "this is a test",
            "https://logo.jpg",
            new ProjectModel.Company("cat"),
            "20150404",
            "active");
    private List<ProjectModel.Project> projects = Arrays.asList(project);
    private ProjectModel.ProjectList projectsList = new ProjectModel.ProjectList(projects);
    private ProjectsListViewModel projectsListViewModel;
    private PublishSubject<ProjectModel.ProjectList> projectsListPublishSubject = PublishSubject.create();
    private LiveData<DataWrapper<List<ProjectModel.MapProject>>> liveData;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Scheduler.Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Before
    @Throws(exceptionClasses = Exception.class)
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        projectsListViewModel = new ProjectsListViewModel(getProjectList);
        liveData = projectsListViewModel.getLiveData();
        when(getProjectList.execute()).thenReturn(projectsListPublishSubject.take(1).singleOrError());
    }

    @Test
    public void testExecuteGetProjectsListSuccess() {
        ProjectModel.MapProject expectedResult = new ProjectModel.MapProject(
                "testing", "this is a test", "https://logo.jpg",
                "cat", "2015-04-04", "active");
        projectsListViewModel.getProjects();
        projectsListPublishSubject.onNext(projectsList);
        Assert.assertEquals(expectedResult, liveData.getValue().getData().get(0));
    }

    @After
    public void tearDownClass() {
        RxAndroidPlugins.reset();
    }

    @Test
    public void testExecuteGetProjectsListError() {
        Throwable throwable = new Throwable();
        projectsListViewModel.getProjects();
        projectsListPublishSubject.onError(throwable);
        Assert.assertNull(liveData.getValue().getData());
    }
}

