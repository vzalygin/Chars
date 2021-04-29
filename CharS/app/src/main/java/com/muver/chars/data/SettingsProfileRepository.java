package com.muver.chars.data;

import android.app.Application;
import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.muver.chars.ServiceLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class SettingsProfileRepository {

    private final SettingsProfileDao _settingsProfileDao;
    private final LiveData<List<SettingsProfile>> _profiles;

    public SettingsProfileRepository(Application application) {
        SettingsProfileDatabase db = SettingsProfileDatabase.getInstance(application);
        this._settingsProfileDao = db.settingsProfileDao();
        _profiles = _settingsProfileDao.getAll();
    }
    
    public LiveData<List<SettingsProfile>> getAll() {
        return _profiles;
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
