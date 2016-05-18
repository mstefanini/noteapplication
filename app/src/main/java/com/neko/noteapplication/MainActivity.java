package com.neko.noteapplication;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.michaldrabik.tapbarmenulib.TapBarMenu;
import com.neko.noteapplication.utils.DataProvider;
import com.neko.noteapplication.utils.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    TapBarMenu tapBarMenu;
    private SimpleAdapter adapter;
    private DataProvider dataProvider = DataProvider.getInstance();

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

        Calendar c = Calendar.getInstance();
        int ora= c.get(Calendar.HOUR_OF_DAY);

        if(ora>=20 || ora<5){
            RelativeLayout rl = (  RelativeLayout )findViewById(R.id.schermo);
            rl.setBackgroundColor(Color.parseColor("#232323"));}



        ListView listView = (ListView)findViewById(R.id.listView);
        List list = new LinkedList();

        list.add(new Note("title","description","22"));
        list.add(new Note("title 2","description 2",":)"));

        CustomAdapter adapter = new CustomAdapter(this, R.layout.lista, list);
        listView.setAdapter(adapter);


        //ArrayList<Note> m_data = dataProvider.getDataArray();

       /* //begin init test data
        final ArrayList<HashMap<Str1ing, Object>> m_data = new ArrayList<HashMap<String, Object>>();

        for(int i=0;i<100;i++){
            HashMap<String, Object> map4 = new HashMap<String, Object>();
            map3.put("maintext", "Cucina");
            map3.put("subtext", "comprare pomodori");
            map3.put("day", "26");
            m_data.add(map3);
        }

        final ListView lv = (ListView) m_this.findViewById(R.id.listView);
        lv.setDivider(null);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        adapter = new SimpleAdapter(m_this,
                m_data,
                R.layout.lista,
                new String[] {"maintext", "subtext", "day"},
                new int[] {R.id.tv_MainText, R.id.tv_SubText,R.id.day});

        adapter.setViewBinder(new SimpleAdapter.ViewBinder()
        {
            public boolean setViewValue(View view, Object data, String textRepresentation)
            {
                if (data == null) //if 2nd line text is null, its textview should be hidden
                {
                    view.setVisibility(View.GONE);
                    return true;
                }
                view.setVisibility(View.VISIBLE);
                return false;
            }

        });
        // Bind to our new adapter.
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {

    \
                Toast.makeText(getApplicationContext(), "You have chosen : "+arg2,Toast.LENGTH_LONG).show();
               //click listener
            }
        });
        //show result*/
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
        // User pressed the search button
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // User changed the text
        return false;
    }





}
