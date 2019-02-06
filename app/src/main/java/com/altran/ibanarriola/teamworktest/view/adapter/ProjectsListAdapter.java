package com.altran.ibanarriola.teamworktest.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.altran.ibanarriola.teamworktest.R;
import com.altran.ibanarriola.teamworktest.repository.model.ProjectModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectsListAdapter extends RecyclerView.Adapter<ProjectsListAdapter.ViewHolder> {

    private List<ProjectModel.MapProject> projects;
    private Context context;

    private OnItemClickListener listener;

    public ProjectsListAdapter(Context context, List<ProjectModel.MapProject> projects) {
        this.context = context;
        this.projects = projects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.projects_list_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProjectModel.MapProject project = projects.get(position);

        holder.name.setText(project.getName());
        holder.company.setText(context.getString(R.string.company, project.getCompany()));
        holder.status.setText(context.getString(R.string.status, project.getStatus()));
        Glide.with(context)
                .load(project.getLogo())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.photo);
        holder.itemCard.setOnClickListener(v -> listener.onMessageItemClick(project));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.project_logo)
        protected ImageView photo;
        @BindView(R.id.project_name)
        protected TextView name;
        @BindView(R.id.project_company)
        protected TextView company;
        @BindView(R.id.project_status)
        protected TextView status;
        @BindView(R.id.Item_Card)
        protected CardView itemCard;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {

        void onMessageItemClick(ProjectModel.MapProject project);
    }
}
