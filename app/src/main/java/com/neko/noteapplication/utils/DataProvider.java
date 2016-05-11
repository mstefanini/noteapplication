package com.neko.noteapplication.utils;

import android.provider.ContactsContract;

/**
 * Created by matteo on 11/05/16.
 */

public class DataProvider {

    public static DataProvider dataProvider = null;

    private DataProvider(){

    }

    public static synchronized DataProvider getInstance(){
        if(dataProvider == null){
            dataProvider = new DataProvider();
        }
        return dataProvider;
    }

}
