package com.muver.chars;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.muver.chars.data.SettingsProfile;
import com.muver.chars.data.SettingsProfileRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private SettingsProfileRepository repository;
    private LiveData<List<SettingsProfile>> allSettingsProfiles;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new SettingsProfileRepository(application);
        allSettingsProfiles = repository.getAllProfiles();
    }
}
