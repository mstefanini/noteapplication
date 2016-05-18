package com.neko.noteapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.michaldrabik.tapbarmenulib.TapBarMenu;
import com.neko.noteapplication.utils.DataProvider;
import com.neko.noteapplication.utils.Note;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, FirstFragment.interfacciaSenzaNomeDiFantasia {

    TapBarMenu tapBarMenu;
    private List<Note> list;
    private SimpleAdapter adapter;
    private DataProvider dataProvider = DataProvider.getInstance();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FirstFragment fragment;
    List list;

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
        final String FRAGMENT = "my_fragment";

        Calendar c = Calendar.getInstance();
        int ora= c.get(Calendar.HOUR_OF_DAY);

        if(ora>=20 || ora<5){
            RelativeLayout rl = (RelativeLayout)findViewById(R.id.schermo);
            rl.setBackgroundColor(Color.parseColor("#232323"));}


        if((fragment = (FirstFragment) fragmentManager.findFragmentByTag(FRAGMENT)) == null){
            fragment = FirstFragment.newInstance();
        }


        ListView listView = (ListView)findViewById(R.id.listView);
         list = dataProvider.getListArray();
        listView.setDivider(null);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Toast.makeText(getApplicationContext(),"posizione:\t"+ position,Toast.LENGTH_LONG).show();

               FragmentTransaction vTrans= fragmentManager.beginTransaction();
                        vTrans.add(R.id.container, fragment, FRAGMENT);
                Note note= (Note) list.get(position);
                Bundle bundle=new Bundle();
                bundle.putString("titolo",note.getTitle());
                bundle.putString("testo",note.getNote());
                fragment.setArguments(bundle);
                vTrans.commit();

                /*Intent intent = new Intent(CurrentActivity.this, TargetActivity.class);
                startActivity(intent);*/
            }
        });

        //list.add(new Note("title","description","22"));
        //list.add(new Note("title 2","description 2",":)"));
        CustomAdapter adapter = new CustomAdapter(this, R.layout.lista, list);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        list.clear();
        list = dataProvider.searchByyTitle(query);
        if(list.isEmpty()) {
            list.add(new Note("Nessun risultato", null, null));
            return true;
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // User changed the text
        return false;
    }


    @Override
    public void VOID() {

    }
}
