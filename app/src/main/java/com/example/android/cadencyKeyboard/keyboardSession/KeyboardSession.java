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

    /**
     * Appends at the bottom of the array a keystroke
     * @param code primary code of the key pressed
     * @param type u or d depending ig the key was pressed or released
     * @param timeStamp timestamp in milisecods of the event
     */
    public void appendKeystroke(int code, char type, Long timeStamp) {
        boolean result =  keystrokeList.add(new KeystrokeEntry(code, type, timeStamp));
        if (!result) throw new RuntimeException("Unknown error adding keystroke to list.");
    }

    /**
     * Pops the first element of the list as string
     * @return element at position 0 of the list as string.
     */
    public String getFirstEntry(){
        return this.keystrokeList.remove(0).toString();
    }

    /**
     * Returns the number of elements in the session.
     * @return number of elements in the session.
     */
    public int getNumberOfEntries(){
        return this.keystrokeList.size();
    }
}
