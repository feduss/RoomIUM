package com.secd.ium.roomium;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class CustomAdapter extends ArrayAdapter<Avviso> {

    public CustomAdapter (Context context, int textViewResourceId, List<Avviso> objects) {
        super(context, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(android.R.layout.simple_list_item_2, null);

        TextView titolo = (TextView)convertView.findViewById(android.R.id.text1);
        String sottotesto;
        TextView descrizione = (TextView)convertView.findViewById(android.R.id.text2);

        Avviso c = getItem(position);

        titolo.setText(c.getTitolo());
        sottotesto = "  " + c.getTesto();

        if (sottotesto.length() > 40) {
            descrizione.setText(sottotesto.substring(0, 38) + "...");
        } else {
            descrizione.setText(sottotesto);
        }

        return convertView;
    }
}
