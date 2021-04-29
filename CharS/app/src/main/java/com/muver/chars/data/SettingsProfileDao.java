package com.muver.chars.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SettingsProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SettingsProfile profile);

    @Delete
    void delete(SettingsProfile profile);

    @Query("SELECT * FROM settings_table ORDER BY id ASC")
    LiveData<List<SettingsProfile>> getAll();

    @Update
    void update(SettingsProfile profile);
}
