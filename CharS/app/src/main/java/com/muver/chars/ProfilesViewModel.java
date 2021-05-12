package com.muver.chars;

import android.app.Application;
import android.content.AsyncQueryHandler;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.muver.chars.data.SettingsProfile;
import com.muver.chars.network.EncryptionPackage;
import com.muver.chars.ui.EncryptionFragment;
import com.muver.chars.util.OperationState;
import com.muver.chars.util.OperationType;

import java.util.List;

import static com.muver.chars.util.OperationState.EncryptionErr;
import static com.muver.chars.util.OperationState.InvalidCheckSum;
import static com.muver.chars.util.OperationState.Ok;
import static com.muver.chars.util.OperationState.ServerUnavaliable;
import static com.muver.chars.util.OperationState.TooSmallContainer;

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

    public void execute(@NonNull String container, @NonNull String key, OperationType type, EncryptionFragment fragment) {
        if (_selected == null) {
            Toast.makeText(getApplication().getApplicationContext(), R.string.not_stated_settings_profile, Toast.LENGTH_SHORT).show();
        } else {
            _selected.execute(container, key, type, new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    if (msg.obj != null) {
                        String result = ((EncryptionPackage)msg.obj).getResult();
                        OperationState state = ((EncryptionPackage)msg.obj).getState();
                        switch (state) {
                            case Ok:
                                fragment.setExecutionResult(result);
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
                    }
                }
            });
        }
    }
}
