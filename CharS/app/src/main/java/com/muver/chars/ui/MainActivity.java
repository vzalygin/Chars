package com.muver.chars.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.muver.chars.ServiceLocator;
import com.muver.chars.ProfilesViewModel;
import com.muver.chars.R;
import com.muver.chars.data.SettingsProfile;
import com.muver.chars.util.EncodingType;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServiceLocator serviceLocator = new ServiceLocator(this);
        ProfilesViewModel viewModel = ServiceLocator.getViewModel();

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.list_fragment_view, ProfilesListFragment.class, null)
                .commit();
    }

    public void showDeleteProfileDialog(SettingsProfile profile, Context context) {
        new AlertDialog.Builder(context)
                .setTitle(getString(R.string.delete_warning_title))
                .setMessage(getString(R.string.delete_warning_text))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ServiceLocator.getViewModel().deleteSettingsProfile(profile);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    public void showEditProfileDialog(SettingsProfile profile) {
        new EditProfileFragment(profile).show(getSupportFragmentManager(), EditProfileFragment.TAG);
    }
}