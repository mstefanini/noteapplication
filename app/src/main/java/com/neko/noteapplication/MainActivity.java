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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.michaldrabik.tapbarmenulib.TapBarMenu;
import com.neko.noteapplication.utils.DBSQLManager;
import com.neko.noteapplication.utils.DataProvider;
import com.neko.noteapplication.utils.MainApp;
import com.neko.noteapplication.utils.Note;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ModifyFrag.IOnDetail {
    public final String FRAGMENT = "my_fragment";
    TapBarMenu tapBarMenu;
    private CustomAdapter adapter;
    private DataProvider dataProvider = DataProvider.getInstance();
    private DBSQLManager dbsqlManager = DBSQLManager.getInstance();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private ModifyFrag fragment;
    private List<Note> list;
    private ListView listView;
    ImageView btnAggiungi;
    Bundle bundle;


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

        Calendar c = Calendar.getInstance();int ora= c.get(Calendar.HOUR_OF_DAY);
        if(ora>=20 || ora<5){RelativeLayout rl = (RelativeLayout)findViewById(R.id.schermo);rl.setBackgroundColor(Color.parseColor("#232323"));}


        if((fragment = (ModifyFrag) fragmentManager.findFragmentByTag(FRAGMENT)) == null) fragment = ModifyFrag.newInstance();


        btnAggiungi = (ImageView)findViewById(R.id.btnAggiungi);
        btnAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = ModifyFrag.newInstance();
                FragmentTransaction vTrans= fragmentManager.beginTransaction();
                vTrans.add(R.id.container, fragment, FRAGMENT);
                vTrans.commit();

            }
        });
        listView = (ListView)findViewById(R.id.listView);
        list = dbsqlManager.allNote();
        listView.setDivider(null);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Toast.makeText(getApplicationContext(),"posizione:\t"+ position,Toast.LENGTH_LONG).show();
                fragment = ModifyFrag.newInstance();
                FragmentTransaction vTrans= fragmentManager.beginTransaction();
                        vTrans.add(R.id.container, fragment, FRAGMENT);
                Note note = list.get(position);
                //Toast.makeText(getApplicationContext(),note.getTitle(),Toast.LENGTH_LONG).show();
                if(fragment != null) {
                    Bundle bundle=new Bundle();
                    bundle.putString("titolo",note.getTitle());
                    bundle.putString("testo",note.getNote());
                    bundle.putString("idNote", note.getId());
                    fragment.setArguments(bundle);
                    vTrans.commit();
                }else{

                }
            }
        });

        adapter = new CustomAdapter(MainApp.getInstance().getContext(), R.layout.lista, list);
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
    public boolean hasWindowFocus() {
        adapter = new CustomAdapter(MainApp.getInstance().getContext(), R.layout.lista, list);
        listView.setAdapter(adapter);
        return super.hasWindowFocus();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        list.clear();
        list = dataProvider.searchByyTitle(query);
        if(list.isEmpty()) {
            list.add(new Note("Nessun risultato", null, null));
        }
        adapter = new CustomAdapter(MainApp.getInstance().getContext(), R.layout.lista, list);
        listView.setAdapter(adapter);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // User changed the text
        return false;
    }


    @Override
    public void onMyClose() {
        if(fragment != null){
            FragmentTransaction vTrans = getSupportFragmentManager().beginTransaction();
            vTrans.remove(fragment);
            //getSupportFragmentManager().popBackStack();
            vTrans.commit();
            Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_LONG).show();

            list = dbsqlManager.allNote();//---------mod
            adapter = new CustomAdapter(MainApp.getInstance().getContext(), R.layout.lista, list);
            listView.setAdapter(adapter);


        }
        else  Toast.makeText(getApplicationContext(),"false",Toast.LENGTH_LONG).show();
    }





}
