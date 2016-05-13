package com.neko.noteapplication.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by matteo on 13/05/16.
 */
public class MainApp extends Application {

    private Context context;
    private static MainApp mainApp;

    public static MainApp getInstance(){
        return mainApp;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mainApp = this;
        context = getApplicationContext();
    }

    public Context getContext(){
        return context;
    }
}
