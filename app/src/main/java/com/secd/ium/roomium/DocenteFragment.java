package com.secd.ium.roomium;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

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

        //Gestione del tap sugli elementi della lista

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                int i;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                Context dialogContext = builder.getContext();
                LayoutInflater inflater = LayoutInflater.from(dialogContext);
                View alertView = inflater.inflate(R.layout.activity_alert_view, null);
                builder.setView(alertView);
                //TableLayout tableLayout = (TableLayout) alertView.findViewById(R.id.table);



                builder.setCancelable(true);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



            }
        });

        return rootView1;




    }

    public static String getUsername(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        return prefs.getString("username", "");

    }






}
