package com.muver.chars.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.muver.chars.R;
import com.muver.chars.ServiceLocator;
import com.muver.chars.util.OperationType;


public class EncryptionFragment extends Fragment {
    private enum Correctness {
        AllOk, NSOperationType, EmptyContainer, EmptySecret
    }

    private EditText _containerText;
    private EditText _result;
    private EditText _secretText;
    private RadioButton _insertButton;
    private RadioButton _takeOutButton;
    private TextView _filledContainer;
    private TextView _secretMessage;
    private Button _shareButton;
    private Button _executeButton;
    private Button _copyButton;

    public EncryptionFragment() {
        super(R.layout.encryption_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _containerText = view.findViewById(R.id.container_view);
        _secretText = view.findViewById(R.id.secret_view);
        _insertButton = view.findViewById(R.id.insert_button);
        _takeOutButton = view.findViewById(R.id.take_out_button);
        _result = view.findViewById(R.id.result_view);
        _filledContainer = view.findViewById(R.id.filled_container_view);
        _secretMessage = view.findViewById(R.id.secret_message_view);
        _shareButton = view.findViewById(R.id.share_button);
        _executeButton = view.findViewById(R.id.execute_button);
        _copyButton = view.findViewById(R.id.copy_button);

        _secretMessage.setVisibility(View.INVISIBLE);
        _insertButton.setOnClickListener(new ClickHandler(this));
        _takeOutButton.setOnClickListener(new ClickHandler(this));
        _shareButton.setOnClickListener(new ClickHandler(this));
        _executeButton.setOnClickListener(new ClickHandler(this));
        _copyButton.setOnClickListener(new ClickHandler(this));
    }

    private Correctness checkCorrectness() {
        if (getOperationType() == null)
            return Correctness.NSOperationType;
        else if (_containerText.getText().toString().isEmpty())
            return Correctness.EmptyContainer;
        else if (_secretText.getText().toString().isEmpty())
            return Correctness.EmptySecret;
        else
            return Correctness.AllOk;
    }

    private OperationType getOperationType() {
        if (_insertButton.isChecked())
            return OperationType.Insert;
        else if (_takeOutButton.isChecked())
            return OperationType.TakeOut;
        else
            return null;
    }

    public void setExecutionResult(String result) {
        _result.setText(result);
    }

    private class ClickHandler implements View.OnClickListener {
        EncryptionFragment fragment;

        public ClickHandler(EncryptionFragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.insert_button:
                    _filledContainer.setVisibility(View.VISIBLE);
                    _secretMessage.setVisibility(View.GONE);
                    _secretText.setVisibility(View.VISIBLE);
                    _containerText.setText("");
                    _result.setText("");
                    break;
                case R.id.take_out_button:
                    _filledContainer.setVisibility(View.GONE);
                    _secretMessage.setVisibility(View.VISIBLE);
                    _secretText.setVisibility(View.GONE);
                    _containerText.setText("");
                    _secretText.setText("");
                    _result.setText("");
                    break;
                case R.id.copy_button:
                    if (_result.getText().length() != 0)
                        ServiceLocator.getProfilesViewModel().copy(_result.getText().toString());
                    else
                        Toast.makeText(getContext(), R.string.empty_result, Toast.LENGTH_LONG).show();
                    break;
                case R.id.execute_button:
                    Correctness c = checkCorrectness();
                    if (c == Correctness.AllOk || (c == Correctness.EmptySecret && getOperationType() == OperationType.TakeOut))
                        ServiceLocator.getProfilesViewModel().execute(_containerText.getText().toString(), _secretText.getText().toString(), getOperationType(), fragment);
                    else if (c == Correctness.NSOperationType)
                        Toast.makeText(getContext(), R.string.not_stated_operation_type, Toast.LENGTH_SHORT).show();
                    else if (c == Correctness.EmptyContainer)
                        Toast.makeText(getContext(), R.string.empty_container, Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), R.string.empty_secret, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.share_button:
                    if (_result.getText().length() != 0)
                        ServiceLocator.getProfilesViewModel().share(_result.getText().toString());
                    else
                        Toast.makeText(getContext(), R.string.empty_result, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
