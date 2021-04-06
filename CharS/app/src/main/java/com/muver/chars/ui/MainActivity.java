package com.muver.chars.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.muver.chars.ServiceLocator;
import com.muver.chars.ProfilesViewModel;
import com.muver.chars.R;
import com.muver.chars.data.SettingsProfile;
import com.muver.chars.util.EncodingType;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServiceLocator serviceLocator = new ServiceLocator(this);
        ProfilesViewModel viewModel = ServiceLocator.getViewModel();

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.list_fragment_view, ProfilesListFragment.class, null)
                .commit();
    }
}