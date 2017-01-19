package com.example.android.cadencyKeyboard;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by matias on 1/18/17.
 */

public class AdminReceiver extends DeviceAdminReceiver {

    void showToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        showToast(context, "Admin privilages enabled");
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "If you disable admin privilages we will not be able to protect you from intruders.";
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        showToast(context, "Admin privilages disabled");
    }

}
