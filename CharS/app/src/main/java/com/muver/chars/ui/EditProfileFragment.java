package com.muver.chars.ui;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.muver.chars.R;
import com.muver.chars.data.SettingsProfile;

import org.w3c.dom.Text;

public class EditProfileFragment extends DialogFragment {
    public static final String TAG = "editProfileDialog";

    private SettingsProfile _profile;

    public EditProfileFragment(SettingsProfile profile) {
        super();
        _profile = profile;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((TextView)view.findViewById(R.id.key_view)).setText(_profile.getKey());
    }
}
