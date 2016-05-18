package com.neko.noteapplication.utils;

import com.couchbase.lite.Document;
import com.couchbase.org.apache.http.entity.mime.content.StringBody;

/**
 * Created by matteo on 12/05/16.
 */

public class Note {
    public String id;
    public String note;
    public String date;
    public String time;
    public String title;

    public static final String TITLE = "title";
    public static final String NOTE = "note";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String ID = "id";

    public Note(String aTitle, String aNote, String aTime, String date){
        this.title = aTitle;
        this.note = aNote;
        this.time = aTime;
        this.date = date;
    }

    public Note(String aID, String aTitle, String aNote, String aTime, String date){
        this.id = aID;
        this.title = aTitle;
        this.note = aNote;
        this.time = aTime;
        this.date = date;
    }
    @Override
    public String toString() {
        return title + ": " + note;
    }

    public static Note createFromDocument(Document document){
        return new Note(
                document.getId(),
                String.valueOf(document.getProperty("Title")),
                String.valueOf(document.getProperty("Note")),
                String.valueOf(document.getProperty("Time")),
                String.valueOf(document.getProperty("Date"))
        );
    }
}
