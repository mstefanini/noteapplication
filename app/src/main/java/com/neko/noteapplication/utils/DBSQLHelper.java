package com.neko.noteapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by matteo on 24/05/16.
 */
public class DBSQLHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Note.db";

    public DBSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + " " + DBSQL.NoteEntry.TABLE_NAME + " (" +
                    DBSQL.NoteEntry.COLUMN_NAME_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DBSQL.NoteEntry.COLUMN_NAME_TITLE + " varchar(255), " +
                    DBSQL.NoteEntry.COLUMN_NAME_TEXT + " TEXT, " +
                    DBSQL.NoteEntry.COLUMN_NAME_DATE + " varchar(255), " +
                    DBSQL.NoteEntry.COLUMN_NAME_TIMER + " varchar(255), " +
                    DBSQL.NoteEntry.COLUMN_NAME_MEDIA + " varchar(255) " + " )");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP DATABASE " + DATABASE_NAME);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
