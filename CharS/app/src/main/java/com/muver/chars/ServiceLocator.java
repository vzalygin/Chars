package com.muver.chars;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.muver.chars.ui.MainActivity;

public class ServiceLocator {

    private static MainActivity _activity;
    private static ProfilesViewModel _viewModel;

    public ServiceLocator(@NonNull MainActivity activity) {
        _activity = activity;
    }

    public static MainActivity getActivity() {
        return _activity;
    }

    public static ProfilesViewModel getViewModel() {
        if (_viewModel == null) {
            _viewModel = new ViewModelProvider((ViewModelStoreOwner) _activity).
                    get(ProfilesViewModel.class);
        }
        return _viewModel;
    }
}
