package com.neko.noteapplication;


import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.michaldrabik.tapbarmenulib.TapBarMenu;
import com.neko.noteapplication.utils.DataProvider;
import com.neko.noteapplication.utils.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

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

        final MainActivity m_this = this;


        ArrayList<Note> m_data = dataProvider.getDataArray();

       /* //begin init test data
        final ArrayList<HashMap<Str1ing, Object>> m_data = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("maintext", "sQuola");
        map1.put("subtext", "leggere libro merlino");
        map1.put("day", "12");
        m_data.add(map1);

        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("maintext", "Stipendio");// no small text of this item!
        map2.put("subtext", "chiamare il PIVA");
        map2.put("day", "02");
        m_data.add(map2);

        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("maintext", "Cucina");
        map3.put("subtext", "comprare pomodori");
        map3.put("day", "26");
        m_data.add(map3);

        for(int i=0;i<100;i++){
            HashMap<String, Object> map4 = new HashMap<String, Object>();
            map3.put("maintext", "Cucina");
            map3.put("subtext", "comprare pomodori");
            map3.put("day", "26");
            m_data.add(map3);
        }*/

        //end init data

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


                Toast.makeText(getApplicationContext(), "You have chosen : "+arg2,Toast.LENGTH_LONG).show();
               //click listener
            }
        });
        //show result
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
