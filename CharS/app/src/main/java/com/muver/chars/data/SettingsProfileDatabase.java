package com.muver.chars.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SettingsProfile.class}, version = 1, exportSchema = false)
public abstract class SettingsProfileDatabase extends RoomDatabase {

    public abstract SettingsProfileDao settingsProfileDao();

    private static volatile SettingsProfileDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static SettingsProfileDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (SettingsProfileDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SettingsProfileDatabase.class, "settings_profile_database").
                            build();
                }
            }
        }
        return INSTANCE;
    }
}
