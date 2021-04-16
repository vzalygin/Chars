package com.muver.chars.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class SettingsProfileRepository {

    private SettingsProfileDao _settingsProfileDao;

    public SettingsProfileRepository(Application application) {
        SettingsProfileDatabase db = SettingsProfileDatabase.getInstance(application);
        this._settingsProfileDao = db.settingsProfileDao();
    }

    public List<SettingsProfile> getAll() {
        List<SettingsProfile> tmp = new ArrayList<SettingsProfile>();
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { tmp.addAll(_settingsProfileDao.getAll()); }
        );
        return tmp;
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

    public void update(SettingsProfile profile) {
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { _settingsProfileDao.update(profile); }
                );
    }
}
