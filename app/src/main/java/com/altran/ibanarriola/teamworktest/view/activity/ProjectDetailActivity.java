package com.altran.ibanarriola.teamworktest.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.altran.ibanarriola.teamworktest.R;
import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectDetailActivity extends AppCompatActivity {

    @BindView(R.id.project_logo)
    ImageView logo;
    @BindView(R.id.project_name)
    TextView name;
    @BindView(R.id.project_description)
    TextView description;
    @BindView(R.id.project_company)
    TextView company;
    @BindView(R.id.project_start_date)
    TextView startDate;
    @BindView(R.id.project_status)
    TextView status;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_detail);
        unbinder = ButterKnife.bind(this);
        setGnomeValues();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setGnomeValues() {
        Intent intent = getIntent();
        ProjectModel.MapProject project = intent.getParcelableExtra("project");
        name.setText(project.getName());
        description.setText(project.getDescription());
        company.setText(getString(R.string.company, project.getCompany()));
        startDate.setText(getString(R.string.start_date, project.getStartDate()));
        status.setText(getString(R.string.status_detail, project.getStatus()));
        Glide.with(this).load(project.getLogo()).apply(RequestOptions.fitCenterTransform())
                .into(logo);
    }
}
