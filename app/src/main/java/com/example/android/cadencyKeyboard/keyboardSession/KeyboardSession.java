package com.example.android.cadencyKeyboard.keyboardSession;

import java.util.ArrayList;

/**
 * Class that contains all the keystrokes for a keyboard session.
 * Created by matias on 12/29/16.
 */

public class KeyboardSession {

    private ArrayList<KeyDownEntry> keystrokeList;
    private String packageName;
    private Long startTime;

    public KeyboardSession(String packageName) {
        this.packageName = packageName;
        this.startTime = System.currentTimeMillis();
        keystrokeList = new ArrayList<KeyDownEntry>();
    }

    public String getPackageName() {
        return packageName;
    }

    /**
     * appends a new entry to the session.
     * @param entry new keystroke entry to append.
     */
    public void appendKeystroke(KeyDownEntry entry) {
        boolean result =  keystrokeList.add(entry);
        if (!result) throw new RuntimeException("Unknown error adding keystroke to list.");
    }

    public Long getStartTime() {
        return startTime;
    }

    /**
     * Pops the first element of the list as string
     * @return element at position 0 of the list as string.
     */
    public KeyDownEntry getFirstEntry(){
        return this.keystrokeList.remove(0);
    }

    /**
     * Returns the number of elements in the session.
     * @return number of elements in the session.
     */
    public int getNumberOfEntries(){
        return this.keystrokeList.size();
    }
}
