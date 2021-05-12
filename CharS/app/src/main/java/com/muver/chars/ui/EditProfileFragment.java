package com.muver.chars.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.muver.chars.R;
import com.muver.chars.ServiceLocator;
import com.muver.chars.data.SettingsProfile;
import com.muver.chars.util.EncodingType;

public class EditProfileFragment extends DialogFragment {
    public static final String TAG = "editProfileDialog";

    private SettingsProfile _profile;
    private EditText _name, _key;
    private RadioButton _standard, _maxCapacity, _onlyInsert, _onlyReplace;
    private Button _delete, _save;

    public EditProfileFragment(SettingsProfile profile) {
        super();
        _profile = profile;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        _name = view.findViewById(R.id.name_view);
        _key = view.findViewById(R.id.key_view);
        _standard = view.findViewById(R.id.standard_button);
        _maxCapacity = view.findViewById(R.id.max_capacity_button);
        _onlyInsert = view.findViewById(R.id.only_insert_button);
        _onlyReplace = view.findViewById(R.id.only_replace_button);
        _delete = view.findViewById(R.id.delete_button);
        _save = view.findViewById(R.id.save_button);

        _delete.setOnClickListener(new ClickHandler());
        _save.setOnClickListener(new ClickHandler());
        _name.setText(_profile.getName());
        _key.setText(_profile.getKey());
        switch (_profile.getType()) {
            case "Standard":
                _standard.setChecked(true);
                break;
            case "MaxCapacity":
                _maxCapacity.setChecked(true);
                break;
            case "OnlyInsert":
                _onlyInsert.setChecked(true);
                break;
            case "OnlyReplace":
                _onlyReplace.setChecked(true);
                break;
            default:
                break;
        }
    }

    private EncodingType getType() {
        if (_standard.isChecked()) return EncodingType.Standard;
        else if (_maxCapacity.isChecked()) return EncodingType.MaxCapacity;
        else if (_onlyInsert.isChecked()) return EncodingType.OnlyInsert;
        else if (_onlyReplace.isChecked()) return EncodingType.OnlyReplace;
        else return null;
    }

    private boolean checkCorrectnessInputData() {
        if (_name.getText().toString().equals("")) {
            Toast.makeText(getContext(), getString(R.string.empty_name), Toast.LENGTH_SHORT).show();
            return false;
        } else if (_key.length() != 8) {
            Toast.makeText(getContext(), getString(R.string.too_small_key), Toast.LENGTH_SHORT).show();
            return false;
        } else if (getType() == null) {
            Toast.makeText(getContext(), getString(R.string.not_definite_type), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private SettingsProfile createProfileByValues() {
        SettingsProfile p = new SettingsProfile(_name.getText().toString(), getType(), _key.getText().toString());
        p.setId(_profile.getId());
        return p;
    }

    private class ClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.save_button:
                    if (checkCorrectnessInputData()) {
                        SettingsProfile profile = createProfileByValues();
                        ServiceLocator.getProfilesViewModel().addSettingsProfile(profile);
                        ServiceLocator.getActivity().removeEditProfileDialog();
                    }
                    break;
                case R.id.delete_button:
                    ServiceLocator.getActivity().showDeleteProfileDialog(_profile, getContext());
                    break;
                default:
                    break;
            }
        }
    }
}
