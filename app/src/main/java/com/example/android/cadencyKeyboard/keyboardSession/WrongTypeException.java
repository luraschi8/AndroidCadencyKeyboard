package com.example.android.cadencyKeyboard.keyboardSession;

/**
 * Exception thrown when the type received in the keystroke entry is not u or d.
 * Created by matias on 12/29/16.
 */

public class WrongTypeException extends RuntimeException {

    String message;

    public WrongTypeException(String message)
    {
        super(message);
        this.message = message;
    }
}
