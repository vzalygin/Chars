package com.muver.chars.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SettingsProfileRepository {

    private SettingsProfileDao settingsProfileDao;
    private LiveData<List<SettingsProfile>> allSettingsProfiles;

    public SettingsProfileRepository(Application application) {
        SettingsProfileDatabase db = SettingsProfileDatabase.getInstance(application);
        this.settingsProfileDao = db.settingsProfileDao;
        allSettingsProfiles = settingsProfileDao.getAll();
    }

    public LiveData<List<SettingsProfile>> getAllProfiles() {
        return allSettingsProfiles;
    }

    public void insert(SettingsProfile profile) {
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { settingsProfileDao.insert(profile);
                });
    }

    public void delete(SettingsProfile profile) {
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { settingsProfileDao.deleteEntity(profile);
                });
    }
}
