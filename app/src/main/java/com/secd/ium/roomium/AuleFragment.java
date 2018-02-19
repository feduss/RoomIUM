package com.secd.ium.roomium;


import android.app.AlertDialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        final ListView listView = (ListView) rootView.findViewById(R.id.listAule);

        //Array per le aule
        String[] array1 = {"Aula A",
                "Aula B",
                "Aula C",
				"Aula D",
				"Aula E",
				"Aula F",
				"Aula G",
                "Aula Magna Matematica",
                "Aula Magna Fisica",
                "Aula Costa",
                "Laboratorio M",
                "Laboratorio T"};
        String[] array2 = {"Proiettore, lavagna nera",
                "",
                "Proiettore, lavagna nera, lavagna bianca",
                "Proiettore, lavagna nera",
                "",
                "Proiettore, lavagna nera, lavagna bianca, condizionatore",
                "Proiettore, lavagna nera, condizionatore",
                "",
				"",
				"Proiettore",
				"Postazioni con PC",
				"Postazioni con PC"};
        int[] array3 = {46,
                46,
                28,
                56,
                20,
                35,
                23,
                120,
				100,
				200,
				30,
				78};

        ArrayList<Aula> vettore = new ArrayList<>();
        Aula room;
        for (int i = 0; i < array1.length; i++){
            room = new Aula();
            room.setNome(array1[i]);
            room.setAccessori(array2[i]);
            room.setPosti(array3[i]);
            vettore.add(room);
        }

        //Creazione adapter e settaggio
        AuleAdapter adapter = new AuleAdapter(getActivity(), android.R.layout.simple_list_item_1, vettore);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aula aula = (Aula)listView.getItemAtPosition(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                Context dialogContext = builder.getContext();
                LayoutInflater inflater = LayoutInflater.from(dialogContext);
                View alertView = inflater.inflate(R.layout.layout_aule_alert, null);

                TextView titolo = (TextView) alertView.findViewById(R.id.textTitolo);
                TextView descrizione = (TextView) alertView.findViewById(R.id.textDescrizione);
                ImageView immagine = (ImageView) alertView.findViewById(R.id.imgSpace);

                String attributi = "Posti disponibili: " + aula.getPosti();
                if (aula.getAccessori() != null) {
                    attributi = attributi + "\n" + aula.getAccessori();
                }

                titolo.setText(aula.getNome());
                descrizione.setText(attributi);
                switch (position) {
                    case 0:
                        immagine.setImageResource(R.drawable.aula_a);
                        break;
                    case 1:
                        immagine.setImageResource(R.drawable.aula_b);
                        break;
                    case 2:
                        immagine.setImageResource(R.drawable.aula_c);
                        break;
                    case 3:
                        immagine.setImageResource(R.drawable.aula_d);
                        break;
                    case 4:
                        immagine.setImageResource(R.drawable.aula_e);
                        break;
                    case 5:
                        immagine.setImageResource(R.drawable.aula_f);
                        break;
                    case 6:
                        immagine.setImageResource(R.drawable.aula_g);
                        break;
                    case 7:
                        immagine.setImageResource(R.drawable.aula_magna_mate);
                        break;
                    case 8:
                        immagine.setImageResource(R.drawable.aula_magna_fis);
                        break;
                    case 9:
                        immagine.setImageResource(R.drawable.aula_costa);
                        TextView luogo = (TextView)alertView.findViewById(R.id.textLocation);
                        luogo.setText("Dip. Anatomia e Istologia Patologica");
                        break;
                    case 10:
                        immagine.setImageResource(R.drawable.lab_m);
                        break;
                    case 11:
                        immagine.setImageResource(R.drawable.lab_t);
                        break;
                    default:
                        immagine.setImageResource(R.drawable.room);
                        break;
                }

                builder.setView(alertView);
                builder.setCancelable(true);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return rootView;
    }
}



class Aula {
    private String nome;
    private String accessori;
    private int posti;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAccessori() {
        return accessori;
    }

    public void setAccessori(String accessori) {
        this.accessori = accessori;
    }

    public int getPosti() {
        return posti;
    }

    public void setPosti(int posti) {
        this.posti = posti;
    }
}


