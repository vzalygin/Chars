package com.muver.chars.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.muver.chars.ProfilesViewModel;
import com.muver.chars.R;
import com.muver.chars.ServiceLocator;
import com.muver.chars.data.SettingsProfile;
import com.muver.chars.util.EncodingType;

import java.sql.Time;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final Adapter adapter = new Adapter(new Adapter.SettingsProfileDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProfilesViewModel viewModel = new ViewModelProvider(this).
                get(ProfilesViewModel.class);
        viewModel.getAllSettingsProfiles().observe(this, adapter::submitList);

        /*  repository.insert();
            repository.insert(new SettingsProfile("Test2", EncodingType.OnlyInsert, "12345678"));*/
        //viewModel.insertSettingsProfile(new SettingsProfile(new Date().toString(), EncodingType.MaxCapacity, "12341234"));
    }
}