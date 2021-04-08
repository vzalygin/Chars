package com.muver.chars.ui;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.muver.chars.data.SettingsProfile;

import java.util.List;

public class Adapter extends ListAdapter<SettingsProfile, ViewHolder> {

    public Adapter(@NonNull DiffUtil.ItemCallback<SettingsProfile> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SettingsProfile current = getItem(position);
        holder.bind(current);
    }

    static class SettingsProfileDiff extends DiffUtil.ItemCallback<SettingsProfile> {

        @Override
        public boolean areItemsTheSame(@NonNull SettingsProfile oldItem, @NonNull SettingsProfile newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SettingsProfile oldItem, @NonNull SettingsProfile newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getType().equals(newItem.getType()) &&
                    oldItem.getKey().equals(newItem.getKey()) &&
                    oldItem.getSelected() == newItem.getSelected();
        }
    }
}
