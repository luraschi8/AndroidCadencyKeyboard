package com.example.android.cadencyKeyboard.keyboardSession;

/**
 * Created by matias on 1/2/17.
 */

public class KeyDownEntry implements KeystrokeEntry {

    private int keycode;
    private float pressure;
    private float x;
    private float y;
    private Long timestamp;

    public KeyDownEntry(int keycode, float pressure, float x, float y, Long timestamp) {
        this.keycode = keycode;
        this.pressure = pressure;
        this.x = x;
        this.y = y;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return  Character.toString((char) this.keycode)+ "-d-" + String.valueOf(this.timestamp) +
                "-" + String.valueOf(pressure) + "-" + String.valueOf(x) + "-" + String.valueOf(y);
    }

}
