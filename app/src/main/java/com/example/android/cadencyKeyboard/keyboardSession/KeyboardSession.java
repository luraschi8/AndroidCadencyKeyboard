package com.example.android.cadencyKeyboard.keyboardSession;

import java.util.ArrayList;

/**
 * Class that contains all the keystrokes for a keyboard session.
 * Created by matias on 12/29/16.
 */

public class KeyboardSession {

    private ArrayList<KeystrokeEntry> keystrokeList;
    private String packageName;

    public KeyboardSession(String packageName) {
        this.packageName = packageName;
        keystrokeList = new ArrayList<KeystrokeEntry>();
    }

    public String getPackageName() {
        return packageName;
    }

    /**
     * appends a new entry to the session.
     * @param entry new keystroke entry to append.
     */
    public void appendKeystroke(KeystrokeEntry entry) {
        boolean result =  keystrokeList.add(entry);
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
