package com.muver.chars.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.muver.chars.R;

public class EncryptionFragment extends Fragment {
    private EditText _container;
    private EditText _secret;
    private RadioButton _insert;
    private RadioButton _take_out;
    private TextView _filled_container;
    private TextView _secret_text;
    private TextView _result;

    public EncryptionFragment() {
        super(R.layout.encryption_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _container = view.findViewById(R.id.container_view);
        _secret = view.findViewById(R.id.secret_view);
        _result = view.findViewById(R.id.result_view);
        _insert = view.findViewById(R.id.insert_rb);
        _take_out = view.findViewById(R.id.take_out_rb);
    }
}
