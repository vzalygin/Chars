package com.muver.chars.ui;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.muver.chars.R;
import com.muver.chars.data.SettingsProfile;
import com.muver.chars.util.EncodingType;

public class Adapter extends ListAdapter<SettingsProfile, ViewHolder> {

    public Adapter(@NonNull DiffUtil.ItemCallback<SettingsProfile> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        view.setOnClickListener(new ClickHandler());
        return ViewHolder.create(view);
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
                    oldItem.getKey().equals(newItem.getKey());
        }
    }

    private static class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }
}
