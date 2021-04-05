package com.muver.chars.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.muver.chars.R;
import com.muver.chars.data.SettingsProfile;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    private SettingsProfile profile;
    private TextView name_textView;
    private TextView info_textView;
    private RadioButton r_button;
    private ImageButton edit_button, delete_button;

    private ViewHolder(@NonNull View itemView) {
        super(itemView);

        name_textView = itemView.findViewById(R.id.name);
        info_textView = itemView.findViewById(R.id.info);

        r_button = itemView.findViewById(R.id.r_button);

        edit_button = itemView.findViewById(R.id.editButton);
        edit_button.setOnClickListener(new ClickHandler());
        delete_button = itemView.findViewById(R.id.deleteButton);
        delete_button.setOnClickListener(new ClickHandler());
    }

    public void bind(SettingsProfile profile) {
        this.profile = profile;

        this.name_textView.setText(this.profile.getName());
        // TODO через serviceLocator получать контекс и брать стрингу из R.strings
        String info = "Ключ: " + this.profile.getKey() + "\nРежим: " + this.profile.getType();
        this.info_textView.setText(info);
    }

    static ViewHolder create(View view) {
        return new ViewHolder(view);
    }

    private class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.editButton:
                    Toast.makeText(v.getContext(), "EDIT" + profile.getName(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.deleteButton:
                    Toast.makeText(v.getContext(), "DELETE" + profile.getName(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
