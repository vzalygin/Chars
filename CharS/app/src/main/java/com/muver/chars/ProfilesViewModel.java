package com.muver.chars;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.muver.chars.data.SettingsProfile;
import com.muver.chars.data.SettingsProfileRepository;

import java.util.List;

public class ProfilesViewModel extends AndroidViewModel {

    private SettingsProfile _selected;
    private MutableLiveData<List<SettingsProfile>> _allSettingsProfiles = new MutableLiveData<>();

    public ProfilesViewModel(@NonNull Application application) {
        super(application);
        SettingsProfile.createRepository(application);
        _allSettingsProfiles.setValue(SettingsProfile.getProfiles());
    }

    public MutableLiveData<List<SettingsProfile>> getAllSettingsProfiles() {
        return _allSettingsProfiles;
    }

    public SettingsProfile getSelected() {
        return _selected;
    }

    public void addSettingsProfile(SettingsProfile profile) {
        _allSettingsProfiles.setValue(SettingsProfile.insert(profile));
    }

    public void deleteSettingsProfile(SettingsProfile profile) {
        _allSettingsProfiles.setValue(SettingsProfile.delete(profile));
    }

    public void setSelected(SettingsProfile profile) {
        _selected = profile;
    }
}
