package com.muver.chars.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.muver.chars.util.EncodingType;

@Entity(tableName = "settings_table")
public class SettingsProfile {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "type")
    private int type;

    @NonNull
    @ColumnInfo(name = "key")
    private String key;

    public SettingsProfile(@NonNull String name, int type, @NonNull String key) {
        this.name = name;
        this.type = type;
        this.key = key;
    }

    public String getName() { return name; }
    public int getType() { return type; }
    public String getKey() { return key; }
}
