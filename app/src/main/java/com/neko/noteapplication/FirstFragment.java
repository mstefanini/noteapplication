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


    public static FirstFragment newInstance(){
        return new FirstFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View vView = inflater.inflate(R.layout.first_fragment, container, false);
        final FirstFragment myfragment=this;


        Bundle bundle=this.getArguments();
        String title=bundle.getString("titolo");
        String testo=bundle.getString("testo");


        EditText miotitolo= (EditText )vView.findViewById(R.id.editText);
        miotitolo.setText(title);

        EditText miadescr= (EditText )vView.findViewById(R.id.editText2);
        miadescr.setText(testo);



        Date cal = (Date) Calendar.getInstance().getTime();
        long str = cal.getSeconds();

        Calendar c = Calendar.getInstance();
        final int giorno= c.get(Calendar.DAY_OF_MONTH);

        ora = (TextView)vView.findViewById(R.id.textViewDate);
        ora.setText("" + cal.getHours() + ":" + cal.getMinutes());

        save = (Button)vView.findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note nota=new Note("titolo","descriptio",""+giorno);
                DataProvider.getInstance().addNote(nota);

                //getActivity().getFragmentManager().beginTransaction().remove().commit();
                //getActivity().getSupportFragmentManager().beginTransaction().remove(myfragment).commit();
                //getActivity().getSupportFragmentManager().popBackStack();


            }
        });




        return vView;


    }

    }

