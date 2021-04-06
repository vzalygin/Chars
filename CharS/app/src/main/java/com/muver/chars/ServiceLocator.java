package com.muver.chars;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.muver.chars.navigator.AppNavigator;
import com.muver.chars.navigator.AppNavigatorImpl;

public class ServiceLocator {

    private static Activity _activity;
    private static ProfilesViewModel _viewModel;
    private static AppNavigator _navigator;

    public ServiceLocator(@NonNull Activity activity) {
        _activity = activity;
        _navigator = new AppNavigatorImpl();
    }

    public static Context getContext() {
        return _activity.getApplicationContext();
    }

    public static Activity getActivity() {
        return _activity;
    }

    public static ProfilesViewModel getViewModel() {
        if (_viewModel == null) {
            _viewModel = new ViewModelProvider((ViewModelStoreOwner) _activity).
                    get(ProfilesViewModel.class);
        }
        return _viewModel;
    }

    public static AppNavigator getNavigator() {
        return _navigator;
    }
}
