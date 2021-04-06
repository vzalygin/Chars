package com.muver.chars.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SettingsProfileRepository {

    private SettingsProfileDao _settingsProfileDao;
    private LiveData<List<SettingsProfile>> _allSettingsProfiles;

    public SettingsProfileRepository(Application application) {
        SettingsProfileDatabase db = SettingsProfileDatabase.getInstance(application);
        this._settingsProfileDao = db.settingsProfileDao();
        _allSettingsProfiles = _settingsProfileDao.getAll();
    }

    public LiveData<List<SettingsProfile>> getAll() {
        return _allSettingsProfiles;
    }

    public void insert(SettingsProfile profile) {
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { _settingsProfileDao.insert(profile); }
                );
    }

    public void delete(SettingsProfile profile) {
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { _settingsProfileDao.delete(profile); }
                );
    }

    public void deleteAll() {
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { _settingsProfileDao.deleteAll(); }
                );
    }

    public void update(SettingsProfile profile) {
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { _settingsProfileDao.update(profile); }
                );
    }
}
