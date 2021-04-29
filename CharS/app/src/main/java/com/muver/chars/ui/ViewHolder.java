package com.muver.chars.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.muver.chars.R;
import com.muver.chars.ServiceLocator;
import com.muver.chars.data.SettingsProfile;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    private SettingsProfile _profile;

    private TextView _nameTextView;
    private TextView _infoTextView;
    private RadioButton _rButton;
    private ImageButton _editButton;

    private ViewHolder(@NonNull View itemView) {
        super(itemView);

        _nameTextView = itemView.findViewById(R.id.name);
        _infoTextView = itemView.findViewById(R.id.info);
        _rButton = itemView.findViewById(R.id.r_button);
        _editButton = itemView.findViewById(R.id.editButton);
        _editButton.setOnClickListener(new ClickHandler());
        itemView.setOnClickListener(new ClickHandler());
    }

    public void bind(SettingsProfile profile) {
        this._profile = profile;

        this._nameTextView.setText(this._profile.getName());
        String info = itemView.getContext().getString(R.string.profile_key) + ": " + this._profile.getKey()
                + "\n" + itemView.getContext().getString(R.string.profile_type) + ": " + this._profile.getType();
        this._infoTextView.setText(info);
        this._rButton.setChecked(this._profile.equals(ServiceLocator.getProfilesViewModel().getSelected()));
    }

    @NonNull
    public RadioButton getRButton() {
        return _rButton;
    }

    static ViewHolder create(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
        return new ViewHolder(view);
    }

    private class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.editButton:
                    ServiceLocator.getActivity().showEditProfileDialog(_profile);
                    break;
                default:
                    ServiceLocator.getProfilesViewModel().setSelected(_profile);
                    ((ProfilesAdapter)getBindingAdapter()).getRadioGroup().checkRadioButton(_rButton);
                    break;
            }
        }
    }
}
