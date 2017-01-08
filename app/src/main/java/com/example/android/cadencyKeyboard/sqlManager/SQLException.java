package com.example.android.cadencyKeyboard.sqlManager;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by matias on 1/8/17.
 */

public class SQLException extends RuntimeException {

    public SQLException (String message) {
        super (message);
    }
}
