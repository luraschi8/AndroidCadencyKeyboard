package com.example.android.cadencyKeyboard.keyboardSession;

/**
 * Container class for a keystroke log entry
 * Created by matias on 12/29/16.
 */

public class KeystrokeEntry {

    private int code; //Primary code of the key pressed
    private char type;  //It can be u (up) or d (down)
    private Long timeStamp;

    public KeystrokeEntry(int code, char type, Long timeStamp){
        if (type != 'u' && type != 'd') throw new WrongTypeException("Type " + type +" received is incorrect.");
        this.code = code;
        this.type = type;
        this.timeStamp = timeStamp;
    }

    public int getCode() {
        return code;
    }

    public char getType() {
        return type;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return Character.toString((char) this.code) + '-' + this.type + '-' + String.valueOf(this.timeStamp);
    }
}
