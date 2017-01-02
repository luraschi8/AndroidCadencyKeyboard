package com.example.android.cadencyKeyboard.sqlManager;

import android.provider.BaseColumns;

/**
 * Created by matias on 1/1/17.
 */

public final class KeystrokeLogContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private KeystrokeLogContract() {}

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LogEntry.TABLE_NAME + " (" +
                    LogEntry._ID + " INTEGER PRIMARY KEY," +
                    LogEntry.COLUMN_NAME_KEYCODE + " TEXT," +
                    LogEntry.COLUMN_NAME_TYPE + " TEXT," +
                    LogEntry.COLUMN_NAME_TIMESTAMP + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LogEntry.TABLE_NAME;

    /* Inner class that defines the table contents */
    public static class LogEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_KEYCODE = "keycode";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
    }
}
