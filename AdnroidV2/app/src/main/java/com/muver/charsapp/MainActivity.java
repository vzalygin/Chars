package com.muver.charsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

enum OperationType { Insert, Extract }

public class MainActivity extends AppCompatActivity {
    ImageButton shareButton;
    Button executeButton;
    RadioButton insertButton;
    RadioButton extractButton;
    RadioButton standardButton;
    RadioButton maxCapacityButton;
    RadioButton insertOnlyButton;
    RadioButton replaceOnlyButton;
    TextView message;
    TextView container;
    TextView key;
    public TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shareButton = findViewById(R.id.shareButton);
        executeButton = findViewById(R.id.executeButton);
        insertButton = findViewById(R.id.insertRB);
        extractButton = findViewById(R.id.extractRB);
        standardButton = findViewById(R.id.standardRB);
        maxCapacityButton = findViewById(R.id.maxCapacityRB);
        insertOnlyButton = findViewById(R.id.insertOnlyRB);
        replaceOnlyButton = findViewById(R.id.replaceOnlyRB);
        message = findViewById(R.id.messageText);
        container = findViewById(R.id.containerText);
        key = findViewById(R.id.keyText);
        result = findViewById(R.id.resultText);

        OnClickHandler onClickHandler = new OnClickHandler(this);
        shareButton.setOnClickListener(onClickHandler);
        executeButton.setOnClickListener(onClickHandler);
    }

    public EncodingType getEncodingType() {
        if (standardButton.isChecked())
            return EncodingType.Standard;
        else if (maxCapacityButton.isChecked())
            return EncodingType.MaxCapacity;
        else if (insertOnlyButton.isChecked())
            return EncodingType.OnlyInsert;
        else if (replaceOnlyButton.isChecked())
            return EncodingType.OnlyReplace;
        else
            return null;
    }

    public OperationType getOperationType() {
        if (insertButton.isChecked())
            return OperationType.Insert;
        else if (extractButton.isChecked())
            return OperationType.Extract;
        else
            return null;
    }
}


class OnClickHandler implements View.OnClickListener {
    MainActivity activity;
    OnClickHandler(AppCompatActivity a) {
        this.activity = (MainActivity) a;
    }

    @Override
    public void onClick(View view) {
        if (ImageButton.class.isInstance(view)) {
            String s = (activity).result.getText().toString();
            if (!s.isEmpty()) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, s);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                activity.startActivity(shareIntent);
            }
            else {
                Toast.makeText(activity, R.string.EmptySendMessage, Toast.LENGTH_LONG).show();
            }
            return;
        }
        OperationType opType = activity.getOperationType();
        EncodingType encType = activity.getEncodingType();
        if (encType != null && opType != null) {
            String container = activity.container.getText().toString();
            String message = activity.message.getText().toString();
            String key = activity.key.getText().toString();
            if (opType == OperationType.Insert) {
                try {
                    if (key.length() == 8)
                        activity.result.setText(CharEncoder.encoding(container, message, key, encType));
                    else
                        throw new InvalidKeyException();
                }
                catch (TooSmallContainerException e) {
                    Toast.makeText(activity, R.string.TooSmallContainer, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    Toast.makeText(activity, R.string.NotFormalKey, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(activity, R.string.Error, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
            else if (opType == OperationType.Extract) {
                try {
                    try {
                        if (key.length() == 8)
                            activity.result.setText(CharEncoder.decoding(container, key, encType));
                        else
                            throw new InvalidKeyException();
                    } catch (InvalidChecksumException e) {
                        Toast.makeText(activity, R.string.InvalidChecksum, Toast.LENGTH_LONG).show();
                        activity.result.setText(CharEncoder.decoding(container, key, EncodingType.OnlyInsert));
                    }
                } catch (InvalidKeyException e) {
                    Toast.makeText(activity, R.string.NotFormalKey, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(activity, R.string.Error, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }
        else if (encType == null){
            Toast.makeText(activity, R.string.EmptyEncodingMode, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(activity, R.string.EmptyOperationMode, Toast.LENGTH_LONG).show();
        }
    }
}