package com.muver.chars.ui;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.muver.chars.data.SettingsProfile;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private CustomRadioGroup _radioGroup = new CustomRadioGroup();;
    private List<SettingsProfile> _profiles;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SettingsProfile current = _profiles.get(position);
        _radioGroup.addRadioButton(holder.getRButton());
        holder.bind(current);
    }

    @Override
    public int getItemCount() {
        return _profiles.size();
    }

    public CustomRadioGroup getRadioGroup() {
        return _radioGroup;
    }

    public List<SettingsProfile> getProfiles() {
        return _profiles;
    }

    public void setProfiles(List<SettingsProfile> _profiles) {
        this._profiles = _profiles;
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
                    oldItem.getKey().equals(newItem.getKey());
        }
    }
}
