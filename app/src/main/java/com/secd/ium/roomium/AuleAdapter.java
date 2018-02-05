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

public class AuleAdapter extends ArrayAdapter<Aula> {

    public AuleAdapter (Context context, int textViewResourceId, List<Aula> objects) {
        super(context, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);

        TextView titolo = (TextView)convertView.findViewById(android.R.id.text1);

        Aula c = getItem(position);

        titolo.setText(c.getNome());

        return convertView;
    }
}
