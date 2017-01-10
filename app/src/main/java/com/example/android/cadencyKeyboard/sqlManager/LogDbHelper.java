package com.example.android.cadencyKeyboard.sqlManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.cadencyKeyboard.keyboardSession.KeyDownEntry;
import com.example.android.cadencyKeyboard.keyboardSession.KeyboardSession;

import static com.example.android.cadencyKeyboard.sqlManager.KeystrokeLogContract.SQL_CREATE_KEYSTROKES;
import static com.example.android.cadencyKeyboard.sqlManager.KeystrokeLogContract.SQL_CREATE_SESSIONS;
import static com.example.android.cadencyKeyboard.sqlManager.KeystrokeLogContract.SQL_DELETE_KEYSTROKES;
import static com.example.android.cadencyKeyboard.sqlManager.KeystrokeLogContract.SQL_DELETE_SESSIONS;

/**
 * Created by matias on 1/1/17.
 */

public class LogDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public LogDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_KEYSTROKES);
        db.execSQL(SQL_CREATE_SESSIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //TODO Revisar esto.
        db.execSQL(SQL_DELETE_KEYSTROKES);
        db.execSQL(SQL_DELETE_SESSIONS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void restartDb(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_SESSIONS);
        db.execSQL(SQL_DELETE_KEYSTROKES);
        db.execSQL(SQL_CREATE_SESSIONS);
        db.execSQL(SQL_CREATE_KEYSTROKES);
        return;
    }

    /**
     * Use this method to insert a new session to the session table.
     * @param session session to be inserted in the db. The entries are not inserted.
     * @return id of the just inserted session. -1 if error.
     */
    public long insertSession (KeyboardSession session) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KeystrokeLogContract.SessionEntry.COLUMN_NAME_STARTTIME, session.getStartTime());
        contentValues.put(KeystrokeLogContract.SessionEntry.COLUMN_NAME_PACKAGE, session.getPackageName());
        return db.insert(KeystrokeLogContract.SessionEntry.TABLE_NAME, null, contentValues);
    }

    public long insertKeystrokeToSession(KeyDownEntry entry, long sessionId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KeystrokeLogContract.LogEntry.COLUMN_NAME_KEYCODE, entry.getKeycode());
        contentValues.put(KeystrokeLogContract.LogEntry.COLUMN_NAME_PRESSURE, entry.getPressure());
        contentValues.put(KeystrokeLogContract.LogEntry.COLUMN_NAME_SESSIONID, sessionId);
        contentValues.put(KeystrokeLogContract.LogEntry.COLUMN_NAME_TIMESTAMPDOWN, entry.getTimestampDown());
        contentValues.put(KeystrokeLogContract.LogEntry.COLUMN_NAME_TIMESTAMPUP, entry.getTimestampUp());
        contentValues.put(KeystrokeLogContract.LogEntry.COLUMN_NAME_X, entry.getX());
        contentValues.put(KeystrokeLogContract.LogEntry.COLUMN_NAME_Y, entry.getY());
        return db.insert(KeystrokeLogContract.LogEntry.TABLE_NAME, null, contentValues);
    }

    public Cursor getKeystrokesFromSession(int session_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + KeystrokeLogContract.LogEntry.TABLE_NAME + " where " +
                KeystrokeLogContract.LogEntry.COLUMN_NAME_SESSIONID +"=" + session_id + "", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, KeystrokeLogContract.LogEntry.TABLE_NAME);
        return numRows;
    }


    public Integer deleteEntry (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(KeystrokeLogContract.LogEntry.TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

/*    public ArrayList<String> getAllEntries() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + KeystrokeLogContract.LogEntry.TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }*/

}
