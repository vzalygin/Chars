package com.muver.chars.data;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.muver.chars.network.EncryptionPackage;
import com.muver.chars.network.Network;
import com.muver.chars.util.EncodingType;
import com.muver.chars.util.OperationType;

import java.util.List;

@Entity(tableName = "settings_table")
public class SettingsProfile {

    private static SettingsProfileRepository _repository;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "type")
    private String type;

    @NonNull
    @ColumnInfo(name = "key")
    private String key;

    public SettingsProfile(@NonNull String name, @NonNull EncodingType type, @NonNull String key) {
        this.type = type.toString();
        this.name = name;
        this.key = key;
    }

    public SettingsProfile() {
        name = "";
        type = "";
        key = "";
    }

    public void setId(int value) { id = value; }
    public void setName(@NonNull String value) { name = value; }
    public void setKey(@NonNull String value) { key = value; }
    public void setType(@NonNull String value) {type = value; }

    public int getId() { return id; }
    @NonNull
    public String getName() { return name; }
    @NonNull
    public String getType() { return type; }
    @NonNull
    public String getKey() { return key; }

    public static void createRepository(Application application) {
        _repository = new SettingsProfileRepository(application);
    }

    public static LiveData<List<SettingsProfile>> getProfiles() {
        return _repository.getAll();
    }

    public static LiveData<List<SettingsProfile>> insert(SettingsProfile profile) {
        if (getProfiles().getValue() != null && getProfiles().getValue().contains(profile))
            _repository.update(profile);
        else
            _repository.insert(profile);
        return getProfiles();
    }

    public static LiveData<List<SettingsProfile>> delete(SettingsProfile profile) {
        _repository.delete(profile);
        return getProfiles();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null)
            return false;
        else if (obj.getClass() == SettingsProfile.class)
            return id == ((SettingsProfile)obj).getId();
        else
            return super.equals(obj);
    }

    public void execute(@NonNull String container, @NonNull String secret, @NonNull OperationType type, Handler handler) {
        EncryptionPackage p = new EncryptionPackage(container, secret, key, EncodingType.valueOf(this.type), type);
        Network.getInstance().execute(p, handler);
    }
}
