package com.muver.chars.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.muver.chars.R;
import com.muver.chars.ServiceLocator;
import com.muver.chars.data.SettingsProfile;

public class MainActivity extends AppCompatActivity {
    private EditProfileFragment _editProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.INTERNET }, 1);

        ServiceLocator.setActivity(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.list_fragment_view, ProfilesListFragment.class, null)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.encryption_fragment_view, EncryptionFragment.class, null)
                .commit();
    }

    public void showEditProfileDialog(SettingsProfile profile) {
        _editProfileFragment = new EditProfileFragment(profile);
        _editProfileFragment.show(getSupportFragmentManager(), EditProfileFragment.TAG);
    }

    public void showDeleteProfileDialog(SettingsProfile profile, Context context) {
        new AlertDialog.Builder(context)
                .setTitle(getString(R.string.warning_title))
                .setMessage(getString(R.string.delete_warning_text))
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    ServiceLocator.getProfilesViewModel().deleteSettingsProfile(profile);
                    removeEditProfileDialog();
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    public void removeEditProfileDialog() {
        getSupportFragmentManager().beginTransaction().remove(_editProfileFragment).commit();
    }
}