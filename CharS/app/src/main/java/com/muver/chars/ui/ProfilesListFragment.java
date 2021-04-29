package com.muver.chars.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.muver.chars.R;
import com.muver.chars.ServiceLocator;
import com.muver.chars.data.SettingsProfile;

public class ProfilesListFragment extends Fragment {
    public ProfilesListFragment() {
        super(R.layout.profiles_list_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        ProfilesAdapter profilesAdapter = new ProfilesAdapter(new ProfilesAdapter.SettingsProfileDiff());

        ServiceLocator.getProfilesViewModel().getAllSettingsProfiles().observeForever(profilesAdapter::submitList);
        recyclerView.setAdapter(profilesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getView().findViewById(R.id.add_profile).setOnClickListener(new ClickHandler());
    }

    private static class ClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ServiceLocator.getActivity().showEditProfileDialog(new SettingsProfile());
        }
    }
}