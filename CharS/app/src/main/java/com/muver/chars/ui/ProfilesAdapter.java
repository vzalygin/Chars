package com.muver.chars.ui;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.muver.chars.ServiceLocator;
import com.muver.chars.data.SettingsProfile;

import java.util.List;

public class ProfilesAdapter extends ListAdapter<SettingsProfile, ViewHolder> {

    private CustomRadioGroup _radioGroup = new CustomRadioGroup();

    public ProfilesAdapter(@NonNull DiffUtil.ItemCallback<SettingsProfile> diffCallback) {
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
        _radioGroup.addRadioButton(holder.getRButton());
        holder.bind(current);
    }

    public CustomRadioGroup getRadioGroup() {
        return _radioGroup;
    }

    static class SettingsProfileDiff extends DiffUtil.ItemCallback<SettingsProfile> {

        @Override
        public boolean areItemsTheSame(@NonNull SettingsProfile oldItem, @NonNull SettingsProfile newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull SettingsProfile oldItem, @NonNull SettingsProfile newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getType().equals(newItem.getType()) &&
                    oldItem.getKey().equals(newItem.getKey());
        }
    }
}
