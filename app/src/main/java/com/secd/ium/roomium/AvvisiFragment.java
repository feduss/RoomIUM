package com.secd.ium.roomium;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AvvisiFragment extends android.support.v4.app.Fragment {


    public AvvisiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Recupero la vista principale del layout
        View fragmentLayout = inflater.inflate(R.layout.fragment_avvisi, container, false);

        //Recupero la lista dall'id nell'xml
        final ListView listView = (ListView)fragmentLayout.findViewById(android.R.id.list);

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
        ArrayList<Avviso> vettore = new ArrayList();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(elemento.getTitolo());
                builder.setMessage(elemento.getTesto() + "\n\nAutore: " + elemento.getAutore());
                builder.setPositiveButton("Chiudi", null);

                //Visualizzazione
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return fragmentLayout;
    }
}

class Avviso {
    private String titolo;
    private String testo;
    private String autore;

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }
}