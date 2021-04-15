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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView)getView().findViewById(R.id.recycler_view);
        Adapter adapter = new Adapter(new Adapter.SettingsProfileDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ServiceLocator.getViewModel().getAllSettingsProfiles().observe(getViewLifecycleOwner(), adapter::submitList);
        getView().findViewById(R.id.add_profile).setOnClickListener(new ClickHandler());
    }

    private static class ClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ServiceLocator.getActivity().showEditProfileDialog(new SettingsProfile());
        }
    }
}