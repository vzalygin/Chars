package com.muver.chars.ui;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;

import com.muver.chars.ServiceLocator;
import com.muver.chars.data.SettingsProfile;

import java.util.List;

public class ProfilesAdapter extends RecyclerView.Adapter<ViewHolder> {

    private CustomRadioGroup _radioGroup = new CustomRadioGroup();;
    private List<SettingsProfile> _profiles;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Toast.makeText(_context, String.valueOf(position), Toast.LENGTH_SHORT).show();
        _radioGroup.addRadioButton(holder.getRButton());
        SettingsProfile current = _profiles.get(position);
        holder.bind(current);
    }

    @Override
    public int getItemCount() {
        return _profiles.size();
    }

    public CustomRadioGroup getRadioGroup() {
        return _radioGroup;
    }

    public void setProfiles(List<SettingsProfile> profiles) {
        Toast.makeText(ServiceLocator.getViewModel().getApplication().getApplicationContext(), String.valueOf(profiles.size()), Toast.LENGTH_SHORT).show();
        this._profiles = profiles;
    }
}
