package com.muver.chars;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class ServiceLocator {

    private static Activity _activity;
    private static ViewModel _viewModel;

    public ServiceLocator(@NonNull Activity activity) {
        _activity = activity;
    }

    public static Context getContext() { return _activity.getApplicationContext(); }

    public static ViewModel getViewModel() {
        if (_viewModel == null) {
            _viewModel = new ViewModelProvider((ViewModelStoreOwner) _activity).
                    get(ViewModel.class);
        }
        return _viewModel;
    }
}
