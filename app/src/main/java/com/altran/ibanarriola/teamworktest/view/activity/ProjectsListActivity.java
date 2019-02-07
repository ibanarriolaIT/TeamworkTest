package com.altran.ibanarriola.teamworktest.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.altran.ibanarriola.teamworktest.R;
import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;
import com.altran.ibanarriola.teamworktest.view.adapter.ProjectsListAdapter;
import com.altran.ibanarriola.teamworktest.view.mvvm.ProjectsListViewModel;
import com.altran.ibanarriola.teamworktest.view.mvvm.ProjectsListViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

public class ProjectsListActivity extends DaggerAppCompatActivity
        implements ProjectsListAdapter.OnItemClickListener {

    @Inject
    ProjectsListViewModelFactory projectsListViewModelFactory;

    ProjectsListViewModel projectsListViewModel;

    @BindView(R.id.projects_list)
    RecyclerView projectsRecyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        projectsListViewModel = ViewModelProviders.of(this, projectsListViewModelFactory).get(ProjectsListViewModel.class);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.list_title);
        unbinder = ButterKnife.bind(this);
        onInit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void onErrorReceivingProjects() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, R.string.error_getting_projects, Toast.LENGTH_LONG);
    }

    public void onInit() {
        progressBar.setVisibility(View.VISIBLE);
        projectsListViewModel.getLiveData().observe(this, projects -> {
            progressBar.setVisibility(View.GONE);
            projectsRecyclerView.setVisibility(View.VISIBLE);
            ProjectsListAdapter adapter = new ProjectsListAdapter(projects);
            adapter.setOnItemClickListener(this);
            RecyclerView.LayoutManager listLayoutManager = new LinearLayoutManager(getApplicationContext());
            projectsRecyclerView.setLayoutManager(listLayoutManager);
            projectsRecyclerView.setAdapter(adapter);
        });
        projectsListViewModel.getProjects();

    }

    @Override
    public void onMessageItemClick(ProjectModel.MapProject project) {
        Intent intent = new Intent(this, ProjectDetailActivity.class);
        intent.putExtra("project", project);
        startActivity(intent);
    }
}
