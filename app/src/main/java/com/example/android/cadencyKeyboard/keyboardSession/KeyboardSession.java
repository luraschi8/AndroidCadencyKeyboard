package com.example.android.cadencyKeyboard.keyboardSession;

import java.util.ArrayList;

/**
 * Class that contains all the keystrokes for a keyboard session.
 * Created by matias on 12/29/16.
 */

public class KeyboardSession {

    private ArrayList<KeystrokeEntry> keystrokeList;

    public KeyboardSession() {
        keystrokeList = new ArrayList<KeystrokeEntry>();
    }

    public void appendKeystroke(int code, char type, Long timeStamp) {
        boolean result =  keystrokeList.add(new KeystrokeEntry(code, type, timeStamp));
        if (!result) throw new RuntimeException("Unknown error adding keystroke to list.");
    }
}
