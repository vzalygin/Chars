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
import com.muver.chars.data.SettingsProfileRepository;
import com.muver.chars.util.InvalidChecksumException;
import com.muver.chars.util.OperationType;
import com.muver.chars.util.TooSmallContainerException;

import java.util.List;
import java.util.Objects;

public class ProfilesViewModel extends AndroidViewModel {

    private SettingsProfile _selected;
    private SettingsProfileRepository _repository;
    private LiveData<List<SettingsProfile>> _allSettingsProfiles;

    public ProfilesViewModel(@NonNull Application application) {
        super(application);
        _repository = new SettingsProfileRepository(application);
        _allSettingsProfiles = _repository.getAll();
        _selected = null;
    }

    public LiveData<List<SettingsProfile>> getAllSettingsProfiles() {
        return _allSettingsProfiles;
    }

    public void addSettingsProfile(SettingsProfile profile) {
        if (_allSettingsProfiles.getValue().contains(profile))
            _repository.update(profile);
        else
            _repository.insert(profile);
    }

    public void deleteSettingsProfile(SettingsProfile profile) {
        _repository.delete(profile);
    }

    public void setSelected(@NonNull SettingsProfile profile) {
        if (_selected == null) {
            try {
                for (SettingsProfile p : Objects.requireNonNull(_allSettingsProfiles.getValue())) {
                    if (p.getSelected() == 1) {
                        _selected = p;
                        break;
                    }
                }
            }
            catch (NullPointerException e) {
                Log.w("ProfilesViewModel", "LiveData value is null.", e);
            }
        }
        if (_selected != null) {
            _selected.setSelected(0);
            _repository.update(_selected);
        }
        _selected = profile;
        _selected.setSelected(1);
        _repository.update(_selected);
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
        try {
            return _selected.execute(container, key, type);
        } catch (TooSmallContainerException e) {
            Toast.makeText(getApplication().getApplicationContext(), R.string.too_small_container, Toast.LENGTH_SHORT).show();
        } catch (InvalidChecksumException e) {
            Toast.makeText(getApplication().getApplicationContext(), R.string.invalid_check_sum, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplication().getApplicationContext(), R.string.invalid_check_sum_info, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplication().getApplicationContext(), R.string.invalid_operation, Toast.LENGTH_SHORT).show();
        }
        return "";
    }
}
