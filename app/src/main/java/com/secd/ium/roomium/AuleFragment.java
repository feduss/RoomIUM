package com.secd.ium.roomium;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuleFragment extends android.support.v4.app.Fragment {


    public AuleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_aule, container, false);

        //Recupero della lista dal file xml
        ListView listView = (ListView) rootView.findViewById(R.id.listAule);

        //Array per le aule
        ArrayList<String> vettore = new ArrayList<>();
        String[] array = {"Aula A",
                "Aula B",
                "Aula C",
                "Aula Magna Matematica",
                "Aula Magna Fisica",
                "Aula Costa",
                "Laboratorio M",
                "Laboratorio T"};
        for(int i = 0; i < array.length; i++){
            vettore.add(array[i]);
        }

        //Creazione adapter e settaggio
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, vettore);
        listView.setAdapter(adapter);

        return rootView;
    }
}
