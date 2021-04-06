package com.muver.chars.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.muver.chars.R;
import com.muver.chars.ServiceLocator;
import com.muver.chars.data.SettingsProfile;
import com.muver.chars.util.EncodingType;

import java.util.Date;

public class ProfilesListFragment extends Fragment {
    public ProfilesListFragment() {
        super(R.layout.profiles_list_fragment);
        ServiceLocator.getViewModel().insertSettingsProfile(new SettingsProfile(new Date().toString(), EncodingType.MaxCapacity, "12341234"));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView)getView();
        Adapter adapter = new Adapter(new Adapter.SettingsProfileDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ServiceLocator.getViewModel().getAllSettingsProfiles().observe(this, adapter::submitList);
    }
}