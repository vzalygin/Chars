package com.muver.chars.ui;

import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomRadioGroup {

    private List<RadioButton> _radioButtons = new ArrayList<>();

    public void addRadioButton(RadioButton button) {
        if (!_radioButtons.contains(button))
            _radioButtons.add(button);
    }

    public void checkRadioButton(RadioButton button) {
        for (RadioButton b : _radioButtons)
            b.setChecked(b == button);
    }
}
