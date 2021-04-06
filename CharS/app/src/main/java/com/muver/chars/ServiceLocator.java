package com.muver.chars;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class ServiceLocator {

    private static Activity _activity;
    private static ProfilesViewModel _viewModel;

    public ServiceLocator(@NonNull Activity activity) {
        _activity = activity;
    }

    public static Context getContext() { return _activity.getApplicationContext(); }

    public static ProfilesViewModel getViewModel() {
        if (_viewModel == null) {
            _viewModel = new ViewModelProvider((ViewModelStoreOwner) _activity).
                    get(ProfilesViewModel.class);
        }
        return _viewModel;
    }
}
