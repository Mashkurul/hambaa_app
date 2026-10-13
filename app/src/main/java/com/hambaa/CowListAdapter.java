package com.hambaa;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CowListAdapter extends RecyclerView.Adapter<CowListAdapter.ViewHolder> {

    private final List<MockData.CowItem> cows;
    private final OnCowClickListener listener;

    public interface OnCowClickListener {
        void onCowClick(MockData.CowItem cow);
    }

    public CowListAdapter(List<MockData.CowItem> cows, OnCowClickListener listener) {
        this.cows = cows;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MockData.CowItem cow = cows.get(position);
        holder.tagStatus.setText(cow.tagId() + " • " + cow.status());
        holder.breed.setText(cow.breed());

        if (cow.isHealthy()) {
            holder.tagStatus.setTextColor(Color.parseColor("#2E7D32"));
        } else {
            holder.tagStatus.setTextColor(Color.parseColor("#D32F2F"));
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCowClick(cow);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cows.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tagStatus, breed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tagStatus = itemView.findViewById(R.id.cowTagStatus);
            breed = itemView.findViewById(R.id.cowBreed);
        }
    }
}
