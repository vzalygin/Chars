package com.muver.chars.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.muver.chars.util.CharEncoder;
import com.muver.chars.util.EncodingType;
import com.muver.chars.util.InvalidChecksumException;
import com.muver.chars.util.OperationType;
import com.muver.chars.util.TooSmallContainerException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

@Entity(tableName = "settings_table")
public class SettingsProfile {

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

    @ColumnInfo(name = "selected")
    private int selected;

    public SettingsProfile(@NonNull String name, @NonNull EncodingType type, @NonNull String key) {
        this.type = type.toString();
        this.name = name;
        this.key = key;
        this.selected = 0;
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
    public void setSelected(int selected) { this.selected = selected; }

    public int getId() { return id; }
    @NonNull
    public String getName() { return name; }
    @NonNull
    public String getType() { return type; }
    @NonNull
    public String getKey() { return key; }
    public int getSelected() { return selected; }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null)
            return false;
        else if (obj.getClass() == SettingsProfile.class)
            return id == ((SettingsProfile)obj).getId();
        else
            return super.equals(obj);
    }

    public String execute(@NonNull String container, @NonNull String secret, @NonNull OperationType type) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, TooSmallContainerException, NoSuchPaddingException, InvalidKeyException, InvalidChecksumException {
        switch (type) {
            case Insert:
                return CharEncoder.encoding(container, secret, key, EncodingType.valueOf(this.type));
            case TakeOut:
                return CharEncoder.decoding(container, key, EncodingType.valueOf(this.type));
            default:
                throw new IllegalArgumentException();
        }
    }
}
