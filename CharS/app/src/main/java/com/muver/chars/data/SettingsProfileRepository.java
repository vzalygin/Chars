package com.muver.chars.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SettingsProfileRepository {

    private SettingsProfileDao settingsProfileDao;
    private LiveData<List<SettingsProfile>> allSettingsProfiles;

    public SettingsProfileRepository(Application application) {
        SettingsProfileDatabase db = SettingsProfileDatabase.getInstance(application);
        this.settingsProfileDao = db.settingsProfileDao();
        allSettingsProfiles = settingsProfileDao.getAll();
    }

    public LiveData<List<SettingsProfile>> getAll() {
        return allSettingsProfiles;
    }

    public void insert(SettingsProfile profile) {
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { settingsProfileDao.insert(profile);
                });
        Log.d("Repos.Insert()", "Профиль вставлен!");
    }

    public void delete(SettingsProfile profile) {
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { settingsProfileDao.delete(profile); }
                );
    }

    public void deleteAll() {
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { settingsProfileDao.deleteAll(); }
                );
    }

    public void update(SettingsProfile profile) {
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { settingsProfileDao.update(profile); }
                );
    }

    public void setSelected(SettingsProfile profile) {
        SettingsProfileDatabase.databaseWriteExecutor.execute(
                () -> { settingsProfileDao.setAllNotSelected();
                        profile.setSelected(1);
                        settingsProfileDao.update(profile);}
        );
    }
}
