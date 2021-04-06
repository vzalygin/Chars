package com.muver.chars.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.muver.chars.ServiceLocator;
import com.muver.chars.ViewModel;
import com.muver.chars.R;
import com.muver.chars.data.SettingsProfile;
import com.muver.chars.util.EncodingType;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ServiceLocator(this);
        ViewModel viewModel = ServiceLocator.getViewModel();


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        Adapter adapter = new Adapter(new Adapter.SettingsProfileDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //viewModel.deleteAll();
        viewModel.getAllSettingsProfiles().observe(this, adapter::submitList);
        viewModel.insertSettingsProfile(new SettingsProfile(new Date().toString(), EncodingType.MaxCapacity, "12341234"));
    }
}