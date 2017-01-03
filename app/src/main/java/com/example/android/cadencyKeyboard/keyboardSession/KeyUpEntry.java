package com.example.android.cadencyKeyboard.keyboardSession;

import android.provider.ContactsContract;

/**
 * Created by matias on 1/2/17.
 */

public class KeyUpEntry implements KeystrokeEntry{
    private int keycode;
    private Long timestamp;

    public KeyUpEntry(int keycode, Long timestamp) {

        this.keycode = keycode;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return Character.toString((char) this.keycode)+ "-u-" + String.valueOf(this.timestamp);
    }

}
