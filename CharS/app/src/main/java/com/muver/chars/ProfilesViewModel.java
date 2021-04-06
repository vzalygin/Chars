package com.muver.chars;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.muver.chars.data.SettingsProfile;
import com.muver.chars.data.SettingsProfileRepository;

import java.util.List;

public class ProfilesViewModel extends AndroidViewModel {

    private SettingsProfile _selected;
    private SettingsProfileRepository _repository;
    private LiveData<List<SettingsProfile>> _allSettingsProfiles;

    public ProfilesViewModel(@NonNull Application application) {
        super(application);
        _repository = new SettingsProfileRepository(application);
        _allSettingsProfiles = _repository.getAll();


        _selected = null;
    }

    public LiveData<List<SettingsProfile>> getAllSettingsProfiles() {
        return _allSettingsProfiles;
    }

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
        if (_selected == null) {
            try {
                for (SettingsProfile p : _allSettingsProfiles.getValue()) {
                    if (p.getSelected() == 1) {
                        _selected = p;
                        break;
                    }
                }
            }
            catch (NullPointerException e) {
                Log.w("ProfilesViewModel", "LiveData value is null.", e);
            }
        }
        if (_selected != null) {
            _selected.setSelected(0);
            _repository.update(_selected);
        }
        _selected = profile;
        _selected.setSelected(1);
        _repository.update(_selected);
    }

    public void deleteAll() {
        _repository.deleteAll();
    }
}
