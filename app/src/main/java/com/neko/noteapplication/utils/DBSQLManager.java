package com.neko.noteapplication.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.neko.noteapplication.utils.DBSQL.NoteEntry;

import java.util.ArrayList;

/**
 * Created by matteo on 24/05/16.
 */
public class DBSQLManager {
    
    private static DBSQLManager instance = null;
    private DBSQLHelper dbHelper = null;

    public static DBSQLManager getInstance(){
        if( instance == null )
            instance = new DBSQLManager();
        return instance;
    }

    private DBSQLManager() {
        dbHelper = new DBSQLHelper(MainApp.getInstance().getContext());
    }

    public void addNote(Note note){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = noteToValues(note);
        long newRowID = db.insert(
                NoteEntry.TABLE_NAME,
                null,
                values
        );
    }

    public void updateNote(Note note){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = noteToValues(note);
        String selection = NoteEntry.COLUMN_NAME_NOTE_ID +  "  LIKE ?";
        String[] selectionArgs = { note.getId() };

        int count = db.update(
                NoteEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public void deleteNote(Note note){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = NoteEntry.COLUMN_NAME_NOTE_ID + " LIKE ?";
        String[] selectionArgs = { note.getId() };

        db.delete(NoteEntry.TABLE_NAME, selection, selectionArgs);
    }

    public ArrayList<Note> searchByTitle(String title){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = NoteEntry.COLUMN_NAME_TITLE +  "  LIKE ?";
        String[] selectionArgs = { title };
        String sortOrder = NoteEntry.COLUMN_NAME_NOTE_ID + " ASC";
        Cursor c = db.query(
                NoteEntry.TABLE_NAME,  // The table to query
                null,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        ArrayList<Note> list = new ArrayList<>();
        if(c.moveToFirst()) {
            int itemID = c.getColumnIndex(NoteEntry.COLUMN_NAME_NOTE_ID);
            int itemTitle = c.getColumnIndex(NoteEntry.COLUMN_NAME_TITLE);
            int itemText = c.getColumnIndex(NoteEntry.COLUMN_NAME_TEXT);
            int itemDate = c.getColumnIndex(NoteEntry.COLUMN_NAME_DATE);
            int itemTimer = c.getColumnIndex(NoteEntry.COLUMN_NAME_TIMER);
            int itemMedia = c.getColumnIndex(NoteEntry.COLUMN_NAME_MEDIA);
            list.add(new Note(String.valueOf(c.getInt(itemID)), c.getString(itemTitle), c.getString(itemText), c.getString(itemTimer), c.getString(itemDate), c.getString(itemMedia)));
            while (c.moveToNext()) {
                list.add(new Note(String.valueOf(c.getInt(itemID)), c.getString(itemTitle), c.getString(itemText), c.getString(itemTimer), c.getString(itemDate), c.getString(itemMedia)));
            }
        }
        c.close();
        return list;

    }


    public ArrayList<Note> allNote(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                NoteEntry.COLUMN_NAME_NOTE_ID,
                NoteEntry.COLUMN_NAME_TITLE,
                NoteEntry.COLUMN_NAME_DATE,
                NoteEntry.COLUMN_NAME_TEXT,
                NoteEntry.COLUMN_NAME_TIMER,
        };
        String sortOrder = NoteEntry.COLUMN_NAME_NOTE_ID + " DESC";
        Cursor c = db.query(
                NoteEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        ArrayList<Note> list = new ArrayList<>();
        if(c.moveToFirst()) {
            int itemID = c.getColumnIndex(NoteEntry.COLUMN_NAME_NOTE_ID);
            int itemTitle = c.getColumnIndex(NoteEntry.COLUMN_NAME_TITLE);
            int itemText = c.getColumnIndex(NoteEntry.COLUMN_NAME_TEXT);
            int itemDate = c.getColumnIndex(NoteEntry.COLUMN_NAME_DATE);
            int itemTimer = c.getColumnIndex(NoteEntry.COLUMN_NAME_TIMER);
            int itemMedia = c.getColumnIndex(NoteEntry.COLUMN_NAME_MEDIA);
            list.add(new Note(String.valueOf(c.getInt(itemID)), c.getString(itemTitle), c.getString(itemText), c.getString(itemTimer), c.getString(itemDate), c.getString(itemMedia)));
            while (c.moveToNext()) {
                list.add(new Note(String.valueOf(c.getInt(itemID)), c.getString(itemTitle), c.getString(itemText), c.getString(itemTimer), c.getString(itemDate), c.getString(itemMedia)));
            }
        }
        c.close();
        return list;
    }

    private ContentValues noteToValues(Note note){
        ContentValues values = new ContentValues();
        values.put(NoteEntry.COLUMN_NAME_TITLE, note.getTitle());
        values.put(NoteEntry.COLUMN_NAME_TEXT, note.getNote());
        values.put(NoteEntry.COLUMN_NAME_DATE, note.getDate());
        values.put(NoteEntry.COLUMN_NAME_TIMER, note.getTime());
        return values;
    }

}
