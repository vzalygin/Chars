package com.muver.chars;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.muver.chars.data.SettingsProfile;
import com.muver.chars.data.SettingsProfileRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private SettingsProfileRepository _repository;
    private LiveData<List<SettingsProfile>> _allSettingsProfiles;

    public ViewModel(@NonNull Application application) {
        super(application);
        _repository = new SettingsProfileRepository(application);
        _allSettingsProfiles = _repository.getAll();
    }

    public LiveData<List<SettingsProfile>> getAllSettingsProfiles() {return _allSettingsProfiles; }

    public void insertSettingsProfile(SettingsProfile profile) {
        _repository.insert(profile);
    }

    public void editSettingsProfile(SettingsProfile profile) {
        _repository.update(profile);
    }

    public void deleteSettingsProfile(SettingsProfile profile) {
        _repository.delete(profile);
    }

    public void setSelected(SettingsProfile profile) {
        _repository.setSelected(profile);
    }

    public void deleteAll() {
        _repository.deleteAll();
    }
}
