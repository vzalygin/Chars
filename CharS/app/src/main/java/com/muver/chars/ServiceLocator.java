package com.muver.chars;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.muver.chars.ui.MainActivity;

public class ServiceLocator {

    private static MainActivity _activity;
    private static ProfilesViewModel _profilesViewModel;
    private static EncryptionViewModel _encryptionViewModel;

    public static MainActivity getActivity() {
        return _activity;
    }

    public static void setActivity(@NonNull MainActivity activity) {
        _activity = activity;
    }

    public static ProfilesViewModel getProfilesViewModel() {
        if (_profilesViewModel == null) {
            _profilesViewModel = new ViewModelProvider((ViewModelStoreOwner) _activity)
                    .get(ProfilesViewModel.class);
        }
        return _profilesViewModel;
    }

    public static EncryptionViewModel getEncryptionViewModel() {
        if (_encryptionViewModel == null) {
            _encryptionViewModel = new ViewModelProvider((ViewModelStoreOwner) _activity)
                    .get(EncryptionViewModel.class);
        }
        return _encryptionViewModel;
    }
}
