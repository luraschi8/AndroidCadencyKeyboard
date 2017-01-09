package com.example.android.cadencyKeyboard.sqlManager;

import android.content.pm.PackageInstaller;
import android.provider.BaseColumns;

/**
 * Created by matias on 1/1/17.
 */

public final class KeystrokeLogContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private KeystrokeLogContract() {}

    public static final String SQL_CREATE_KEYSTROKES =
            "CREATE TABLE " + LogEntry.TABLE_NAME + " (" +
                    LogEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    LogEntry.COLUMN_NAME_KEYCODE + " INTEGER," +
                    LogEntry.COLUMN_NAME_SESSIONID + " INTEGER NOT NULL, " +
                    LogEntry.COLUMN_NAME_TIMESTAMPUP + " TEXT," +
                    LogEntry.COLUMN_NAME_TIMESTAMPDOWN + " TEXT," +
                    LogEntry.COLUMN_NAME_PRESSURE + " FLOAT," +
                    LogEntry.COLUMN_NAME_X + " FLOAT," +
                    LogEntry.COLUMN_NAME_Y + " FLOAT )";

    public static final String SQL_DELETE_KEYSTROKES =
            "DROP TABLE IF EXISTS " + LogEntry.TABLE_NAME;

    public static final String SQL_CREATE_SESSIONS =
            "CREATE TABLE " + SessionEntry.TABLE_NAME + " (" +
                    SessionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SessionEntry.COLUMN_NAME_PACKAGE + " TEXT," +
                    SessionEntry.COLUMN_NAME_STARTTIME + " TEXT )";

    public static final String SQL_DELETE_SESSIONS =
            "DROP TABLE IF EXISTS " + SessionEntry.TABLE_NAME;

    /* Inner class that defines the table contents */
    public static class LogEntry implements BaseColumns {
        public static final String TABLE_NAME = "Keystrokes";
        public static final String COLUMN_NAME_SESSIONID = "session_id";
        public static final String COLUMN_NAME_KEYCODE = "keycode";
        public static final String COLUMN_NAME_TIMESTAMPUP = "timestampUp";
        public static final String COLUMN_NAME_TIMESTAMPDOWN = "timestampDown";
        public static final String COLUMN_NAME_PRESSURE = "pressure";
        public static final String COLUMN_NAME_X = "x";
        public static final String COLUMN_NAME_Y = "y";
    }

    public static class SessionEntry implements BaseColumns {
        public static final String TABLE_NAME = "Sessions";
        public static final String COLUMN_NAME_PACKAGE = "package";
        public static final String COLUMN_NAME_STARTTIME = "startTime";
    }
}
