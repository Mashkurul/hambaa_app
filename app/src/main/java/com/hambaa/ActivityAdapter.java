package com.hambaa;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private final List<MockData.ActivityItem> activities;

    public ActivityAdapter(List<MockData.ActivityItem> activities) {
        this.activities = activities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MockData.ActivityItem item = activities.get(position);
        holder.title.setText(item.title());
        holder.meta.setText(item.subtitle() + " • " + item.timeAgo());
        holder.tag.setText(item.tagText());
        if (item.isGreenTag()) {
            holder.tag.setTextColor(Color.parseColor("#2E7D32"));
        } else {
            holder.tag.setTextColor(Color.parseColor("#668075"));
        }
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, meta, tag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.activityTitle);
            meta = itemView.findViewById(R.id.activityMeta);
            tag = itemView.findViewById(R.id.activityTag);
        }
    }
}
