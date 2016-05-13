package com.neko.noteapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.michaldrabik.tapbarmenulib.TapBarMenu;
import com.neko.noteapplication.utils.DataProvider;
import com.neko.noteapplication.utils.Note;

public class MainActivity extends AppCompatActivity {

    private DataProvider dataProvider = DataProvider.getInstance();
    private TapBarMenu tapBarMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tapBarMenu = (TapBarMenu)findViewById(R.id.tapBarMenu);

        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                tapBarMenu.toggle();
                Log.d("DEBUG", dataProvider.getDataArray().toString());
                Log.d("DEBUG", dataProvider.getNoteById(3).toString());
                Note note = dataProvider.searchByTitle("Title 56");
                if(note != null)
                    Log.d("DEBUG", dataProvider.searchByTitle("Title 0").toString());
                else
                    Log.d("DEBUG", "Ricerca vuota");

                note = dataProvider.searchByTitle("NON C'E' IL TITOLO");
                if(note != null)
                    Log.d("DEBUG", dataProvider.searchByTitle("Title 0").toString());
                else
                    Log.d("DEBUG", "Ricerca vuota");

            }
        });
    }
}
