package com.neko.noteapplication.utils;

import java.util.Date;

/**
 * Created by matteo on 12/05/16.
 */

public class Note {
    public String note;
    public Date date;
    public String time;
    public String title;

    @Override
    public String toString() {
        return title + ": " + note;
    }
}
