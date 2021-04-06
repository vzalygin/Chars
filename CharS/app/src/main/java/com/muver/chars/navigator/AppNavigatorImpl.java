package com.muver.chars.navigator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.muver.chars.R;
import com.muver.chars.ServiceLocator;

public class AppNavigatorImpl implements AppNavigator {
    private Object _confirm;

    @Override
    public boolean navigateTo(ScreenType type) {
        switch (type) {
            case DeleteWarningDialog:
                _confirm = null;
                new AlertDialog.Builder(ServiceLocator.getActivity().getApplicationContext())
                        .setTitle(ServiceLocator.getContext().getString(R.string.delete_warning_title))
                        .setMessage(ServiceLocator.getContext().getString(R.string.delete_warning_text))
                        .setPositiveButton(android.R.string.yes, new DialogClickHandler())
                        .setNegativeButton(android.R.string.cancel, new DialogClickHandler())
                        .show();

                return false;
            case EditProfileFragment:
                return false;
            default:
                return false;
        }
    }

    private static class DialogClickHandler implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(ServiceLocator.getContext(), which, Toast.LENGTH_SHORT).show();
        }
    }
}
