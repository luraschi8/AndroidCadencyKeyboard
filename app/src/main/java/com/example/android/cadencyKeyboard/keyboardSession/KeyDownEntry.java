package com.example.android.cadencyKeyboard.keyboardSession;

/**
 * Created by matias on 1/2/17.
 */

public class KeyDownEntry {

    private int keycode;
    private float pressure;
    private float x;
    private float y;
    private Long timestampDown;
    private Long timestampUp;

    public KeyDownEntry(int keycode, float pressure, float x, float y, Long timestampDown, Long timestampUp) {
        this.keycode = keycode;
        this.pressure = pressure;
        this.x = x;
        this.y = y;
        this.timestampDown = timestampDown;
        this.timestampUp = timestampUp;
    }

    /**
     * Class to String.
     * @return String with the format keycoda-d-timestampDown-u-timestampUp-pressure-PixelX-PixelY
     */
    @Override
    public String toString() {
        return  Character.toString((char) this.keycode)+ "-d-" + String.valueOf(this.timestampDown) +
                "-u-"+ String.valueOf(this.timestampUp) + "-" + String.valueOf(pressure) + "-" +
                String.valueOf(x) + "-" + String.valueOf(y);
    }

    public int getKeycode() {
        return keycode;
    }

    public float getPressure() {
        return pressure;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Long getTimestampDown() {
        return timestampDown;
    }

    public Long getTimestampUp() {
        return timestampUp;
    }
}
