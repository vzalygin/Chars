package com.muver.chars;

import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.muver.chars.data.SettingsProfile;
import com.muver.chars.util.OperationState;
import com.muver.chars.util.OperationType;

import java.util.List;

public class ProfilesViewModel extends AndroidViewModel {

    private SettingsProfile _selected;
    private final LiveData<List<SettingsProfile>> _allSettingsProfiles;

    public ProfilesViewModel(@NonNull Application application) {
        super(application);
        SettingsProfile.createRepository(application);
        _allSettingsProfiles = SettingsProfile.getProfiles();
    }

    public LiveData<List<SettingsProfile>> getAllSettingsProfiles() {
        return _allSettingsProfiles;
    }

    public SettingsProfile getSelected() {
        return _selected;
    }

    public void addSettingsProfile(SettingsProfile profile) {
        SettingsProfile.insert(profile);
    }

    public void deleteSettingsProfile(SettingsProfile profile) {
        SettingsProfile.delete(profile);
    }

    public void setSelected(SettingsProfile profile) {
        _selected = profile;
    }

    public void copy(@NonNull String text) {
        ClipboardManager clipboard = (ClipboardManager) getApplication().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(text, text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplication().getApplicationContext(), R.string.copy_successful, Toast.LENGTH_SHORT).show();
    }

    public void share(@NonNull String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        ServiceLocator.getActivity().startActivity(shareIntent);
    }

    public String execute(@NonNull String container, @NonNull String key, OperationType type) {
        if (_selected == null) {
            Toast.makeText(getApplication().getApplicationContext(), R.string.not_stated_settings_profile, Toast.LENGTH_SHORT).show();
            return "";
        }
        Object[] tmp = _selected.execute(container, key, type);
        String result = (String) tmp[0];
        OperationState state = (OperationState) tmp[1];
        switch (state) {
            case Ok:
                break;
            case EncryptionErr:
                Toast.makeText(getApplication().getApplicationContext(), R.string.invalid_operation, Toast.LENGTH_SHORT).show();
                break;
            case InvalidCheckSum:
                Toast.makeText(getApplication().getApplicationContext(), R.string.invalid_check_sum, Toast.LENGTH_SHORT).show();
                break;
            case TooSmallContainer:
                Toast.makeText(getApplication().getApplicationContext(), R.string.too_small_container, Toast.LENGTH_SHORT).show();
                break;
            case ServerUnavaliable:
                Toast.makeText(getApplication().getApplicationContext(), R.string.server_unavailable, Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.w("ProfViewModel::execute", "Unknown state: " + state);
                break;
        }
        return result;
    }
}
