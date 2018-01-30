package com.secd.ium.roomium;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocenteFragment extends android.support.v4.app.Fragment {

    TextView datiDoc;

    public DocenteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup                  container, Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.activity_docente, container, false);


        //dati docente
        View rootView1 = inflater.inflate(R.layout.fragment_docente, container, false);

        datiDoc= (TextView) rootView1.findViewById(R.id.nome_cognome_doc);


        String username=getUsername(getContext());

        if(username!=null){
            if(datiDoc!=null)datiDoc.setText(username);
        }
        //fine dati docente

        //Lista Orario
        //Recupero la lista dall'id dell'xml
        ListView listView = (ListView) rootView1.findViewById(R.id.listview);

        //Array di stringhe degli avvisi
        String[] array = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì"};

        //Arraylist che contiene gli elementi della listview
        ArrayList<String> array1 = new ArrayList();

        for (int i = 0; i < array.length; i++) {
            array1.add(array[i]);
        }

        //Costruisco l'adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1 , array1);

        listView.setAdapter(adapter);

        return rootView1;
    }

    public static String getUsername(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        return prefs.getString("username", "");

    }



}
