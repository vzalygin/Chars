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

    @NonNull
    @ColumnInfo(name = "type")
    private String type;

    @NonNull
    @ColumnInfo(name = "key")
    private String key;

    @ColumnInfo(name = "selected")
    private int selected;

    public SettingsProfile(@NonNull String name, @NonNull EncodingType type, @NonNull String key) {
        this.name = name;
        this.type = type.toString();
        this.key = key;
        this.selected = 0;
    }

    public SettingsProfile() {
        name = "";
        type = "";
        key = "";
    }

    public void setName(@NonNull String value) { name = value; }
    public void setKey(@NonNull String value) { key = value; }
    public void setType(@NonNull String value) {type = value; }
    public void setSelected(int selected) { this.selected = selected; }

    @NonNull
    public String getName() { return name; }
    @NonNull
    public String getType() { return type; }
    @NonNull
    public String getKey() { return key; }
    public int getSelected() { return selected; }
}
