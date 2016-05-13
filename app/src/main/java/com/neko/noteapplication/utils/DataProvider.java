package com.neko.noteapplication.utils;

import android.provider.ContactsContract;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by matteo on 11/05/16.
 */

public class DataProvider {

    public static DataProvider dataProvider = null;
    private ArrayList<Note> dataArray = new ArrayList<>();

    private DataProvider() {
        for(int i = 0; i < 101; i++){
            Note nota = new Note();
            nota.date = new Date();
            nota.time = null;
            nota.title = "Title " + i;
            nota.note = "Nota " + i;
            dataArray.add(nota);
        }
    }

    public static DataProvider getInstance(){
        if(dataProvider == null){
            dataProvider = new DataProvider();
        }
        return dataProvider;
    }

    public ArrayList<Note> getDataArray(){
        return dataArray;
    }

    public Note getNoteById(int i){
        return dataArray.get(i);
    }

    public Note searchByTitle(String aTitle){
        for(Note n: dataArray){
            if(n.title.equals(aTitle))
                return n;
        }
        return null;
    }

}
