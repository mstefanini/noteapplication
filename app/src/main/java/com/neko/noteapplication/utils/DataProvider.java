package com.neko.noteapplication.utils;

import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.couchbase.lite.ChangesOptions;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by matteo on 11/05/16.
 */

public class DataProvider {

    private MainApp mainApp = MainApp.getInstance();
    private Database database = null;
    public static DataProvider dataProvider = null;
    public static final String DB_NAME = "couchbaseevents";
    public static final String TAG = "couchbaseevents";


    private DataProvider() {
        initCB();
    }

    public static DataProvider getInstance(){
        if(dataProvider == null){
            dataProvider = new DataProvider();
        }
        return dataProvider;
    }

    public ArrayList<Note> getDataArray(){
        return null;
    }

    public Note getNoteById(int i){
        return null;
    }

    public Note searchByTitle(String aTitle){
        return null;
    }

    private void initCB(){
        Manager manager = null;
        try{
            manager = new Manager(new AndroidContext(mainApp.getContext()), Manager.DEFAULT_OPTIONS );
            database = manager.getDatabase(DB_NAME);

        } catch (Exception e){
            Log.e(TAG, "Error getting database", e);
            return;
        }
    }

    private String createDocuments(Note note){
        Document document = database.createDocument();
        String documentID = document.getId();
        Map<String, Object> obj = new HashMap<>();
        obj.put("Title", note.title);
        obj.put("Note", note.note);
        obj.put("Time", note.time);
        obj.put("Date", note.date);
        try{
            document.putProperties(obj);
        }catch (CouchbaseLiteException e){
            Log.e(TAG, "Error putting element");
        }
        return documentID;
    }

    private Document retrieveDocument(String documentID){
        Document retrieveDocument = database.getDocument(documentID);
    }

}
