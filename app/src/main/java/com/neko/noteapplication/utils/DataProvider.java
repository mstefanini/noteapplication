package com.neko.noteapplication.utils;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.UnsavedRevision;
import com.couchbase.lite.android.AndroidContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Created by matteo on 11/05/16.
 */

public class DataProvider {

    private MainApp mainApp = MainApp.getInstance();
    private Database database = null;
    private Manager manager = null;
    public static DataProvider dataProvider = null;
    public static final String DB_NAME = "couchbaseevents";
    public static final String TAG = "couchbaseevents";
    public static final String COUCH = "coucherror";


    private DataProvider() {
        initCB();
    }

    public static DataProvider getInstance(){
        if(dataProvider == null){
            dataProvider = new DataProvider();
        }
        return dataProvider;
    }

    public void addNote(Note note){
        Document document = database.createDocument();
        HashMap<String, Object> hashMap = setHashMap(note);
        hashMap.put("id", document.getId());
        try{
            document.putProperties(hashMap);
        } catch (CouchbaseLiteException e){
            Log.e(COUCH, "I cannot add note");
        }
    }

    public ArrayList<Note> getDataArray(){
        ArrayList<Note> noteArrayList = new ArrayList<>();
        try {
            Query allDocumentsQuery = database.createAllDocumentsQuery();
            QueryEnumerator queryResult = allDocumentsQuery.run();
            for (Iterator<QueryRow> it = queryResult; it.hasNext(); ) {
                QueryRow row = it.next();
                noteArrayList.add(Note.createFromDocument(row.getDocument()));
            }
        } catch (Exception e) {
            Log.e(TAG, "An error happened", e);
        }
        return noteArrayList;
    }

    public List<Note> getListArray(){
        List<Note> noteList = new ArrayList<>();
        try {
            Query allDocumentsQuery = database.createAllDocumentsQuery();
            QueryEnumerator queryResult = allDocumentsQuery.run();
            for (Iterator<QueryRow> it = queryResult; it.hasNext(); ) {
                QueryRow row = it.next();
                noteList.add(Note.createFromDocument(row.getDocument()));
            }
        } catch (Exception e) {
            Log.e(TAG, "An error happened", e);
        }
        return noteList;
    }

    public Note getNoteById(String i){
        return Note.createFromDocument(database.getDocument(i));
    }

    public List<Note> searchByyTitle(String aTitle){
        ArrayList<Note> note = new ArrayList<>();
        try{
            Query allDocumentsQuery = database.createAllDocumentsQuery();
            QueryEnumerator queryResults = allDocumentsQuery.run();
            for(Iterator<QueryRow> it = queryResults; it.hasNext(); ){
                QueryRow row = it.next();
                if(aTitle.equals(row.getDocument().getProperty(Note.TITLE))){
                   note.add(Note.createFromDocument(row.getDocument()));
                }
            }
        } catch (CouchbaseLiteException e){
            Log.e(COUCH, "Problem in find element");
        }
        return note;

    }

    private void initCB(){
        try{
            manager = new Manager(new AndroidContext(mainApp.getContext()), Manager.DEFAULT_OPTIONS );
            database = manager.getDatabase(DB_NAME);
        } catch (Exception e){
            Log.d(TAG, "Error getting database", e);
        }
    }

    private String createDocuments(Note note){
        Document document = database.createDocument();
        String documentID = document.getId();
        Map<String, Object> obj = setHashMap(note);
        try{
            document.putProperties(obj);
        }catch (CouchbaseLiteException e){
            Log.d(TAG, "Error putting element");
        }
        return documentID;
    }


    private HashMap<String, Object> setHashMap(Note note){
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("Title", note.title);
        obj.put("Note", note.note);
        obj.put("Time", note.time);
        obj.put("Date", note.date);
        return obj;
    }

    private void initDebugDB(){
        for(int i=0; i<100; i++){
            addNote(new Note("Titolo", "Testo nota", "TEMPO", "DATA"));
        }
    }

    public void updateNote(Note note){
        Document doc = database.getDocument(note.getId());
        Map<String, Object> properties = new HashMap<>();
        properties.putAll(doc.getProperties());
        addProperties(properties, note);
        try {
            doc.putProperties(properties);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

    }

    public HashMap<String, Object> noteToMap(Note note){
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("id", note.getId());
        temp.put("note", note.getNote());
        temp.put("date", note.getDate());
        temp.put("time", note.getTime());
        temp.put("title", note.getTime());

        return temp;
    }

    void addProperties(Map<String, Object> temp, Note note){
        if(temp != null) {
            temp.put("id", note.getId());
            temp.put("note", note.getNote());
            temp.put("date", note.getDate());
            temp.put("time", note.getTime());
            temp.put("title", note.getTime());
        }
    }
}
