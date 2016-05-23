package com.neko.noteapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.michaldrabik.tapbarmenulib.TapBarMenu;
import com.neko.noteapplication.utils.DataProvider;
import com.neko.noteapplication.utils.Note;

import java.util.Calendar;
import java.util.Date;

public class FirstFragment extends Fragment {
    public interface interfacciaSenzaNomeDiFantasia{
        public void VOID();
    }

    Button save;
    TextView ora;
    TapBarMenu tapBarMenu;

    public static FirstFragment newInstance(){
        return new FirstFragment();
    }
    String title;
    String testo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View vView = inflater.inflate(R.layout.first_fragment, container, false);
        final FirstFragment myfragment=this;

        tapBarMenu = (TapBarMenu)vView.findViewById(R.id.tapBarMenu);
        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                tapBarMenu.toggle();
            }
        });

        Bundle bundle=this.getArguments();
        if(getArguments() != null){
            if(bundle.getString("titolo")!=null) {
                title=bundle.getString("titolo");
                EditText miotitolo= (EditText )vView.findViewById(R.id.editText);
                miotitolo.setText(title);
            }
            if(bundle.getString("testo")!=null) {
                testo=bundle.getString("testo");
                EditText miadescr= (EditText )vView.findViewById(R.id.editText2);
                miadescr.setText(testo);
            }
        }



        save = (Button)vView.findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                final int giorno= c.get(Calendar.DAY_OF_MONTH);

                Date cal = (Date) Calendar.getInstance().getTime();
                long str = cal.getSeconds();


                /* = (TextView)vView.findViewById(R.id.textViewDate);
                ora.setText("" + cal.getHours() + ":" + cal.getMinutes());*/




                Note nota=new Note("titolo","descriptio",""+giorno +" " + cal.getHours() + ":" + cal.getMinutes());
                DataProvider.getInstance().addNote(nota);

                //getActivity().getFragmentManager().beginTransaction().remove().commit();
                //getActivity().getSupportFragmentManager().beginTransaction().remove(myfragment).commit();
                //getActivity().getSupportFragmentManager().popBackStack();


            }
        });




        return vView;


    }

    }

