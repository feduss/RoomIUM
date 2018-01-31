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
                TableLayout tableLayout = (TableLayout) alertView.findViewById(R.id.table);

                for (i = 0; i < 1; i++) {

                    TableRow tableRow1 = new TableRow(dialogContext);
                    tableRow1.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TableRow tableRow2 = new TableRow(dialogContext);
                    tableRow2.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TableRow tableRow3 = new TableRow(dialogContext);
                    tableRow3.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TableRow tableRow4 = new TableRow(dialogContext);
                    tableRow4.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TableRow tableRow5 = new TableRow(dialogContext);
                    tableRow5.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TableRow tableRow6 = new TableRow(dialogContext);
                    tableRow6.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TableRow tableRow7 = new TableRow(dialogContext);
                    tableRow7.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TableRow tableRow8 = new TableRow(dialogContext);
                    tableRow8.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TableRow tableRow9 = new TableRow(dialogContext);
                    tableRow9.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TableRow tableRow10 = new TableRow(dialogContext);
                    tableRow10.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TableRow tableRow11 = new TableRow(dialogContext);
                    tableRow11.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TableRow tableRow12 = new TableRow(dialogContext);
                    tableRow12.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    TextView textView1 = new TextView(dialogContext);
                    textView1.setText("9:00");
                    tableRow1.addView(textView1);

                    TextView textView2 = new TextView(dialogContext);
                    textView2.setText("9:50");
                    tableRow2.addView(textView2);

                    TextView textView3 = new TextView(dialogContext);
                    textView3.setText("10:40");
                    tableRow3.addView(textView3);

                    TextView textView4 = new TextView(dialogContext);
                    textView4.setText("11:00");
                    tableRow4.addView(textView4);

                    TextView textView5 = new TextView(dialogContext);
                    textView5.setText("11:50");
                    tableRow5.addView(textView5);

                    TextView textView6 = new TextView(dialogContext);
                    textView6.setText("12:40");
                    tableRow6.addView(textView6);

                    TextView textView7 = new TextView(dialogContext);
                    textView7.setText("15:00");
                    tableRow7.addView(textView7);

                    TextView textView8 = new TextView(dialogContext);
                    textView8.setText("15:50");
                    tableRow8.addView(textView8);

                    TextView textView9 = new TextView(dialogContext);
                    textView9.setText("17:00");
                    tableRow9.addView(textView9);

                    TextView textView10 = new TextView(dialogContext);
                    textView10.setText("17:50");
                    tableRow10.addView(textView10);

                    TextView textView11 = new TextView(dialogContext);
                    textView11.setText("18:40");
                    tableRow11.addView(textView11);

                    TextView textView12 = new TextView(dialogContext);
                    textView12.setText("19:00");
                    tableRow12.addView(textView12);


                    tableLayout.addView(tableRow1);
                    tableLayout.addView(tableRow2);
                    tableLayout.addView(tableRow3);
                    tableLayout.addView(tableRow4);
                    tableLayout.addView(tableRow5);
                    tableLayout.addView(tableRow6);
                    tableLayout.addView(tableRow7);
                    tableLayout.addView(tableRow8);
                    tableLayout.addView(tableRow9);
                    tableLayout.addView(tableRow10);
                    tableLayout.addView(tableRow11);
                    tableLayout.addView(tableRow12);
                }

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
