package com.neko.noteapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.michaldrabik.tapbarmenulib.TapBarMenu;
import com.neko.noteapplication.utils.DataProvider;
import com.neko.noteapplication.utils.Note;

import java.util.Calendar;
import java.util.Date;

public class ModifyFrag extends Fragment {

    public interface IOnDetail {void onMyClose();}
    IOnDetail mListener = new IOnDetail() {
        @Override
        public void onMyClose() {

        }
    };
    private static final String TITOLO="titolo";
    private static final String TESTO="testo";
    Button save;
    TextView ora;
    TapBarMenu aMenu;
    Button bclose;
    EditText miotitolo=null;
    EditText miadescr=null;
    String idNote;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getActivity() instanceof IOnDetail){
            mListener = (IOnDetail)getActivity();
        }
    }

    public static ModifyFrag newInstance(){

        return new ModifyFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


         View vView = inflater.inflate(R.layout.first_fragment, container, false);

        aMenu= (TapBarMenu)vView.findViewById(R.id.tapBarMenu2);
        aMenu.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                aMenu.toggle();
            }
        });

        bclose= (Button)vView.findViewById(R.id.bClose);
        bclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMyClose();
            }
        });

        miotitolo= (EditText )vView.findViewById(R.id.editText);
        miotitolo.setText("");

        miadescr= (EditText )vView.findViewById(R.id.editText2);
        miadescr.setText("");

        Bundle bundle;
        if((bundle = getArguments()) != null){
            //Log.d ("LOG DI TAZZINA",bundle.getString(TITOLO));
            miotitolo.setText(bundle.getString(TITOLO));
            miadescr.setText(bundle.getString(TESTO));
            idNote = bundle.getString("idNote");
            }




/*
        for (String key: bundle.keySet())
        {
            Log.d ("LOG DI TAZZINA", bundle.getString(key));
        }

*/


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

                Note nota = new Note(idNote, miotitolo.getText().toString(), miadescr.getText().toString(), "" + giorno + " " + cal.getHours() + ":" + cal.getMinutes());

                if(getArguments() != null) {
                    DataProvider.getInstance().updateNote(nota);
                    mListener.onMyClose();
                } else {
                    DataProvider.getInstance().addNote(nota);
                    mListener.onMyClose();
                }
                //getActivity().getFragmentManager().beginTransaction().remove().commit();
                //getActivity().getSupportFragmentManager().beginTransaction().remove(myfragment).commit();
                //getActivity().getSupportFragmentManager().popBackStack();


            }
        });




        return vView;


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}

