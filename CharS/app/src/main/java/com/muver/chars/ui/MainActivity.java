package com.muver.chars.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.muver.chars.ViewModel;
import com.muver.chars.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final Adapter adapter = new Adapter(new Adapter.SettingsProfileDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ViewModel viewModel = new ViewModelProvider(this).
                get(ViewModel.class);
        viewModel.getAllSettingsProfiles().observe(this, adapter::submitList);

        /*  repository.insert();
            repository.insert(new SettingsProfile("Test2", EncodingType.OnlyInsert, "12345678"));*/
        //viewModel.insertSettingsProfile(new SettingsProfile(new Date().toString(), EncodingType.MaxCapacity, "12341234"));
    }
}