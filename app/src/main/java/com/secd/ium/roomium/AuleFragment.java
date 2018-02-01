package com.secd.ium.roomium;


import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    /*
    // CONTROPROVA DELLA LISTA AVVISI E DEL SUO LISTENER. QUI FUNZIONA :|
        //Recupero la lista dall'id nell'xml
        final ListView listView = (ListView)rootView.findViewById(R.id.listAule);

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
        //ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, array1);
        CustomAdapter adapter = new CustomAdapter(getActivity(), android.R.layout.simple_list_item_2, vettore);
        listView.setAdapter(adapter);

        //Settaggio del listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Recupero dell'avviso dalla lista
                Avviso elemento = (Avviso)listView.getItemAtPosition(position);
                //String titolo = (String)listView.getItemAtPosition(position);

                //Creazione e settaggio di un'alert dialog per la visualizzazione
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(elemento.getTitolo());
                //builder.setTitle(titolo);
                builder.setMessage(elemento.getTesto() + "\n\nAutore: " + elemento.getAutore());
                //builder.setMessage("In corso...");
                builder.setPositiveButton("Chiudi", null);

                //Visualizzazione
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        */

        //Recupero della lista dal file xml
        final ListView listView = (ListView) rootView.findViewById(R.id.listAule);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aula = (String)listView.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle(aula);
                builder.setMessage("Posti illimitati\nProiettore!"); //questa poi si rivede...
                builder.setPositiveButton("Chiudi", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return rootView;
    }
}
