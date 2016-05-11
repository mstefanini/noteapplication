package com.neko.noteapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.michaldrabik.tapbarmenulib.TapBarMenu;

public class MainActivity extends AppCompatActivity {

    TapBarMenu tapBarMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tapBarMenu = (TapBarMenu)findViewById(R.id.tapBarMenu);

        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                tapBarMenu.toggle();
            }
        });
    }
}
