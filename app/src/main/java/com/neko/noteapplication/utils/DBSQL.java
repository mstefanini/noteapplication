package com.neko.noteapplication.utils;

import android.provider.BaseColumns;

/**
 * Created by matteo on 24/05/16.
 */
public final class DBSQL {
    public DBSQL() {

    }

    public final class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NAME_NOTE_ID = "noteid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_TEXT = "text";
        public static final String COLUMN_NAME_MEDIA = "media";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TIMER = "timer";
    }
}
