package com.neko.noteapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.neko.noteapplication.utils.Note;

import java.util.List;

/**
 * Created by robert on 18/05/16.
 */
public class CustomAdapter extends ArrayAdapter<Note> {


    public CustomAdapter (Context context, int textViewResourceId,
                          List objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.lista, null);
        TextView nome = (TextView) convertView.findViewById(R.id.tv_MainText);
        TextView testo = (TextView) convertView.findViewById(R.id.tv_SubText);
        TextView data = (TextView)  convertView.findViewById(R.id.day);
        Note c = getItem(position);
        nome.setText(c.getTitle());
        testo.setText(c.getTime());
        data.setText(c.getNote());
        return convertView;
    }


}