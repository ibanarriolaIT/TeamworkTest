package com.altran.ibanarriola.teamworktest.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.altran.ibanarriola.teamworktest.R;
import com.altran.ibanarriola.teamworktest.databinding.ProjectDetailBinding;
import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;

public class ProjectDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
        setContentView(R.layout.project_detail);
        ProjectDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.project_detail);
        Intent intent = getIntent();
        ProjectModel.MapProject project = intent.getParcelableExtra("project");
        binding.setProject(project);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.detail_title);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.animation_back_enter, R.anim.animation_back_leave);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
