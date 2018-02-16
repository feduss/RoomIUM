package com.secd.ium.roomium;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvvisiDocenteFragment extends android.support.v4.app.Fragment {

    public AvvisiDocenteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup                  container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_avvisi_docente, container, false);

        //Recupero la lista dall'id nell'xml
        final ListView listView = (ListView)rootView.findViewById(R.id.listAvvisiD);

        //Array di stringhe degli avvisi
        String[] array1 = {"Sospensione delle lezioni",
                "Pinna ha pubblicato i voti",
                "Inagibilità del Palazzo delle Scienze",
                "Il ricevimento di Prof. Fenu è annullato",
                "Possibilità di tirocinio presso Ferrero"};
        String[] array2 = {"Le lezioni sono sospese per la pausa natalizia. Buone feste a tutti!",
                "Evviva!",
                "La caldaia è esplosa di nuovo...",
                "Lo hanno chiamato per rammendare la rete del PdS.",
                "La Kinder approva."};
        String[] array3 = {"Gianni Fenu",
                "Giovanni Michele Pinna",
                "Gianni Fenu",
                "Gianni Fenu",
                "Riccardo Scateni"
        };

        //Arraylist che contiene gli elementi della listview
        final ArrayList<Avviso> vettore = new ArrayList<>();
        Avviso nota;
        for(int i=0; i<array1.length; i++){
            nota = new Avviso();
            nota.setTitolo(array1[i]);
            nota.setTesto(array2[i]);
            nota.setAutore(array3[i]);
            vettore.add(nota);
        }

        //Costruisco l'adapter
        CustomAdapter adapter = new CustomAdapter(getActivity(), android.R.layout.simple_list_item_2, vettore);
        listView.setAdapter(adapter);

        //Settaggio del listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Recupero dell'avviso dalla lista
                Avviso elemento = (Avviso)listView.getItemAtPosition(position);

                //Creazione e settaggio di un'alert dialog per la visualizzazione
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
                builder.setTitle(elemento.getTitolo());
                builder.setMessage(elemento.getTesto() + "\n\nAutore: " + elemento.getAutore());
                builder.setPositiveButton("Chiudi", null);

                //Visualizzazione
                android.support.v7.app.AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        final ImageButton avvisoButton = (ImageButton)rootView.findViewById(R.id.addButton);

        avvisoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creazione della vista
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                Context dialogContext = builder.getContext();
                LayoutInflater inflater = LayoutInflater.from(dialogContext);
                View alertView = inflater.inflate(R.layout.layout_avvisi_alert, null);

                //Recupero degli elementi della vista
                final EditText titolo = (EditText)alertView.findViewById(R.id.editTitolo);
                final EditText avviso = (EditText)alertView.findViewById(R.id.editAvviso);
                final TextView contoCaratteri = (TextView)alertView.findViewById(R.id.counterView);

                //TextWatcher per la lunghezza caratteri
                TextWatcher watch = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        contoCaratteri.setText(String.valueOf(s.length()) + "/120");

                        if(s.length() <= 120) {
                            contoCaratteri.setTextColor(Color.parseColor("#000000"));
                        } else {
                            contoCaratteri.setTextColor(Color.parseColor("#FF0080"));
                        }
                    }
                };
                avviso.addTextChangedListener(watch);

                //Inserimento della view
                builder.setView(alertView);
                builder.setTitle("Nuovo avviso");
                builder.setPositiveButton("Conferma", null);

                AlertDialog dialog = builder.create();
                final Avviso nuovoAvviso = new Avviso();

                //Gestione del bottone sovrascrivendo il metodo onShow del dialog
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(final DialogInterface dialog) {
                        //Recupero del bottone del dialog
                        Button button = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE);

                        //Creazione del nuovo listener
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(titolo.getText().toString() == null
                                        || titolo.getText().toString().equals("")
                                        || avviso.getText().toString() == null
                                        || avviso.getText().toString().equals("")) {

                                    if(titolo.getText().toString() == null
                                            || titolo.getText().toString().equals("")) {
                                        titolo.setError("Riempi il campo");
                                    }

                                    if(avviso.getText().toString() == null
                                            || avviso.getText().toString().equals("")) {
                                        avviso.setError("Riempi il campo");
                                    }
                                } else {
                                    //Inserimento del nuovo avviso nella lista
                                    nuovoAvviso.setTitolo(titolo.getText().toString());
                                    nuovoAvviso.setTesto(avviso.getText().toString());
                                    nuovoAvviso.setAutore(getUsername(getContext())); //TODO recuperare il nome del docente
                                    vettore.add(nuovoAvviso);

                                    //Spostamento del nuovo avviso in testa
                                    for (int i = vettore.size()-1; i > 0; i--) {
                                        Avviso toMove = vettore.get(i);
                                        vettore.set(i, vettore.get(i-1));
                                        vettore.set(i-1, toMove);
                                    }

                                    //Nuova visualizzazione della lista
                                    CustomAdapter adapter = new CustomAdapter(getActivity(), android.R.layout.simple_list_item_2, vettore);
                                    listView.setAdapter(adapter);

                                    //Conferma e chiusura del dialog
                                    Toast.makeText(getActivity(), "Avviso inserito", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        });
                    }
                });

                dialog.show();
            }
        });

        return rootView;
    }

    public static String getUsername(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        return prefs.getString("username", "");

    }
}
