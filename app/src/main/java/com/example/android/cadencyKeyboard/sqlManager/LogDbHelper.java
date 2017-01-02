package com.example.android.cadencyKeyboard.sqlManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import static com.example.android.cadencyKeyboard.sqlManager.KeystrokeLogContract.SQL_CREATE_ENTRIES;
import static com.example.android.cadencyKeyboard.sqlManager.KeystrokeLogContract.SQL_DELETE_ENTRIES;

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
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Use this methid to insert a new entry in the db.
     * @param keycode primary code of the key pressed
     * @param type type of event. It can be u or d.
     * @param timestamp timestamp of the event/
     * @return returns the id of the new entry or -1 if error.
     */
    public long insertEntry (String keycode, String type, String timestamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KeystrokeLogContract.LogEntry.COLUMN_NAME_KEYCODE, keycode);
        contentValues.put(KeystrokeLogContract.LogEntry.COLUMN_NAME_TYPE, type);
        contentValues.put(KeystrokeLogContract.LogEntry.COLUMN_NAME_TIMESTAMP, timestamp);
        return db.insert(KeystrokeLogContract.LogEntry.TABLE_NAME, null, contentValues);
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + KeystrokeLogContract.LogEntry.TABLE_NAME + " where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, KeystrokeLogContract.LogEntry.TABLE_NAME);
        return numRows;
    }

/*    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }*/

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
