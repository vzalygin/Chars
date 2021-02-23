package com.muver.chars.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.muver.chars.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.switch_on).setOnClickListener(new OnClickListenerImpl());
        findViewById(R.id.switch_off).setOnClickListener(new OnClickListenerImpl());
    }

    class OnClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.switch_on:
                    SetVisibility(true);
                    break;
                case R.id.switch_off:
                    SetVisibility(false);
                    break;
            }
        }
    }

    private void SetVisibility(boolean isVisible) {
        if (isVisible)
            findViewById(R.id.something).setVisibility(View.VISIBLE);
        else
            findViewById(R.id.something).setVisibility(View.GONE);
    }
}