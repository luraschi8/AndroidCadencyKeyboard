/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.cadencyKeyboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.inputmethodcommon.InputMethodSettingsFragment;
import com.example.android.cadencyKeyboard.sqlManager.LogDbHelper;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Displays the IME preferences inside the input method setting.
 */
public class ImePreferences extends PreferenceActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static String alertAddressKey = "alert_email_address";
    public static final int INTENT_REQUEST_CODE = 1001;
    public static final String SHARE_LOG_EMAIL = "luraschi8@gmail.com";

    @Override
    public Intent getIntent() {
        final Intent modIntent = new Intent(super.getIntent());
        modIntent.putExtra(EXTRA_SHOW_FRAGMENT, Settings.class.getName());
        modIntent.putExtra(EXTRA_NO_HEADERS, true);
        return modIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We overwrite the title of the activity, as the default one is "Voice Search".
        setTitle(R.string.settings_name);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected boolean isValidFragment(final String fragmentName) {
        return Settings.class.getName().equals(fragmentName);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ImePreferences Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public static class Settings extends InputMethodSettingsFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setInputMethodSettingsCategoryTitle(R.string.language_selection_title);
            setSubtypeEnablerTitle(R.string.select_language);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.ime_preferences);

            final SwitchPreference emailAlertPref = (SwitchPreference) findPreference("email_alert_switch");
            emailAlertPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if ((boolean) newValue) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        final EditText input = new EditText(getContext());
                        input.setSingleLine();
                        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
                        String hint = "your@email.com";
                        if (sharedPreferences.contains(alertAddressKey)) {
                            input.setText(sharedPreferences.getString(alertAddressKey, "default"));
                        } else  input.setHint(hint);
                        builder
                                .setTitle("Email Alert Address")
                                .setMessage("Set your email address.")
                                .setView(input)
                                .setCancelable(false)
                                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //For old versions of Android. Button is not created if there is no handler.
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        final AlertDialog dialog = builder.create();
                        dialog.show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v) {
                                String email = input.getText().toString();
                                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                    SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(alertAddressKey, input.getText().toString());
                                    editor.apply();
                                    dialog.dismiss();
                                    Toast.makeText(getActivity(), "Email address saved.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "Invalid email address.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    return true;
                }
            });

            Preference deleteLogsPref = (Preference) findPreference("delete_logs");
            deleteLogsPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder
                            .setTitle("Delete Information?")
                            .setMessage("Do you really want to delete all your information?" +
                                    "If you do it, the keyboard may not be able to detect intruders properly.")
                            .setCancelable(false)
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    LogDbHelper helper = new LogDbHelper(getActivity());
                                    helper.restartDb();
                                    Toast.makeText(getActivity(), "Database erased.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return true;
                }
            });

            Preference saveDataPref = (Preference) findPreference("send_data");
            saveDataPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    File logFile;
                    try {
                        logFile = File.createTempFile("keystroke", "log", getContext().getExternalCacheDir());
                    } catch (IOException e) {
                        Log.e("ERROR.IO", e.getMessage());
                        Toast.makeText(getContext(), "Error creating file.", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    FileOutputStream outputStream;
                    try {
                        outputStream = new FileOutputStream(logFile);
                        String manufacturerModel = Build.MANUFACTURER + " " + Build.MODEL;
                        outputStream.write(manufacturerModel.getBytes());
                    } catch (FileNotFoundException e) {
                        Log.e("ERROR.IO", e.getMessage());
                        Toast.makeText(getContext(), "Error opening stream to file.", Toast.LENGTH_SHORT).show();
                        return true;
                    } catch (IOException e) {
                        Log.e("ERROR.IO", e.getMessage());
                        Toast.makeText(getContext(), "Error writing to file.", Toast.LENGTH_SHORT).show();
                        return true;
                    }


                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

                    emailIntent .setType("text/plain");
                    emailIntent.setData(Uri.parse("mailto:" + SHARE_LOG_EMAIL));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My email's subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "My email's body");
                    // the attachment
                    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(logFile));
                    startActivity(Intent.createChooser(emailIntent , "Chose email client."));

                    return true;
                }

            });

        }

    }
}
