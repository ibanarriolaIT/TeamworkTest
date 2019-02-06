package com.altran.ibanarriola.teamworktest.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.altran.ibanarriola.teamworktest.R;
import com.altran.ibanarriola.teamworktest.databinding.ProjectsListElementBinding;
import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;

import java.util.List;

public class ProjectsListAdapter extends RecyclerView.Adapter<ProjectsListAdapter.ProjectViewHolder> {

    private List<ProjectModel.MapProject> projects;
    private LayoutInflater layoutInflater;

    private OnItemClickListener listener;

    public ProjectsListAdapter(List<ProjectModel.MapProject> projects) {
        this.projects = projects;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ProjectsListElementBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.projects_list_element, parent, false);
        return new ProjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        holder.binding.setProject(projects.get(position));
        holder.binding.ItemCard.setOnClickListener(v -> listener.onMessageItemClick(projects.get(position)));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {

        private final ProjectsListElementBinding binding;

        public ProjectViewHolder(ProjectsListElementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {

        void onMessageItemClick(ProjectModel.MapProject project);
    }
}
