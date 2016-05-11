package com.neko.noteapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG ="DetailActivity";
     
    TextView mText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(TAG,"D: onCreate");

        mText = (TextView)findViewById(R.id.titleView);




    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "D: onStop(");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "D: onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "D: onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "D: onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "D: onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "D: onStart");
    }
}
