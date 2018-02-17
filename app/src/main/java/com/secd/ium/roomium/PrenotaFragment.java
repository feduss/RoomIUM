package com.secd.ium.roomium;


import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrenotaFragment extends android.support.v4.app.Fragment {


    public PrenotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_prenota, container, false);

        final Spinner aula = (Spinner) rootView.findViewById(R.id.spinneraula);
        String[] itemsAula = new String[]{"Seleziona l'aula","A", "B", "C", "Laboratorio T", "D", "E", "F", "Laboratorio M", "Matematica", "Fisica"};
        ArrayAdapter<String> adapterAula = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsAula);
        aula.setAdapter(adapterAula);

        final Spinner materia = (Spinner) rootView.findViewById(R.id.spinnermateria);
        String[] itemsMateria = new String[]{"Seleziona la materia","PR1","MD", "FDI", "CDI", "ASD", "ARE"};
        ArrayAdapter<String> adapterMateria = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsMateria);
        materia.setAdapter(adapterMateria);

        Button data = (Button) rootView.findViewById(R.id.datebutton);

        //final TextView orari = (TextView) rootView.findViewById(R.id.testoorari);

        final ListView lv = (ListView) rootView.findViewById(R.id.listOrari);

        final Model[] modelItems = new Model[3];
        modelItems[0] = new Model("09:00-10:40", 0);
        modelItems[1] = new Model("11:00-13:30", 0);
        modelItems[2] = new Model("15:00-17:30", 0);

        CheckboxAdapter adapterPrenota = new CheckboxAdapter(getActivity(), modelItems);

        lv.setAdapter(adapterPrenota);



        final Button conferma = (Button) rootView.findViewById(R.id.prenotaconfirm);

        data.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getFragmentManager(), "datePicker");
            }
        });


        conferma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder builder;

                if (aula.getSelectedItemPosition() == 0 || materia.getSelectedItemPosition() == 0 /**||
                        (modelItems[0].getValue() == 0 && modelItems[1].getValue() == 0 && modelItems[2].getValue() == 0)*/){
                    if (aula.getSelectedItemPosition() == 0) ((TextView)aula.getSelectedView()).setError("Scegli un'aula");
                    if (materia.getSelectedItemPosition() == 0) ((TextView)materia.getSelectedView()).setError("Scegli una materia");
                    //if (modelItems[0].getValue() == 0 && modelItems[1].getValue() == 0 && modelItems[2].getValue() == 0) orari.setError("Scegli almeno una fascia oraria");
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                    builder = new AlertDialog.Builder(getContext());
                    }

                    builder.setTitle("Prenotare l'aula selezionata?");
                    builder.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "Aula prenotata", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Annulla", null);
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });

        return rootView;
    }
}
