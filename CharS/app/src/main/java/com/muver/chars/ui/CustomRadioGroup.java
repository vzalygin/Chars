package com.muver.chars.ui;

import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class CustomRadioGroup {

    private List<RadioButton> _radioButtons = new ArrayList<>();

    public void addRadioButton(RadioButton button) {
        _radioButtons.add(button);
    }

    public void checkRadioButton(RadioButton button) {
        for (RadioButton b : _radioButtons)
            b.setChecked(b == button);
    }
}
