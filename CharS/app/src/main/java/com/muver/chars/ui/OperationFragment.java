package com.muver.chars.ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.muver.chars.R;

public class OperationFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}