package com.muver.chars.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SettingsProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SettingsProfile profile);

    @Delete
    void deleteEntity(SettingsProfile profile);

    @Query("DELETE FROM settings_table")
    void deleteAll();

    @Query("SELECT * FROM settings_table ORDER BY name ASC")
    LiveData<List<SettingsProfile>> getAll();
}
