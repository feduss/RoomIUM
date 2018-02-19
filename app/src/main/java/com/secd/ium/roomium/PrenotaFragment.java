package com.secd.ium.roomium;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrenotaFragment extends android.support.v4.app.Fragment {


    public PrenotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_prenota, container, false);

        //Recupero dei riferimenti
        final LinearLayout linearInsivibile= (LinearLayout) rootView.findViewById(R.id.layoutInvisibile);
        final Spinner aula = (Spinner) rootView.findViewById(R.id.spinneraula);
        final Spinner materia = (Spinner) rootView.findViewById(R.id.spinnermateria);
        final TextView data = (TextView) rootView.findViewById(R.id.datepicker);
        final TextView testoOrari = (TextView) rootView.findViewById(R.id.testoorari);

        //Setting spinner per l'aula
        String[] itemsAula = new String[] {"Seleziona l'aula", "Aula A", "Aula B",
                "Aula C", "Aula D", "Aula E", "Aula F", "Aula G",
                "Aula Magna Matematica", "Aula Magna Fisica", "Aula Costa",
                "Laboratorio M", "Laboratorio T"};
        final ArrayAdapter<String> adapterAula = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsAula);
        aula.setAdapter(adapterAula);

        //Attivazione visibilità della lista scelte su aula
        aula.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (aula.getSelectedItemPosition() != 0 && materia.getSelectedItemPosition() != 0 &&
                        !(data.getText().toString().equals("Seleziona la data"))){
                    linearInsivibile.setVisibility(View.VISIBLE);
                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        //Setting spinner per la materia
        String[] itemsMateria = new String[]{"Seleziona la materia","PR1","MD", "FDI", "CDI", "ASD", "ARE"};
        final ArrayAdapter<String> adapterMateria = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsMateria);
        materia.setAdapter(adapterMateria);

        //Attivazione visibiltà dela lista scelte su materia
        materia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (aula.getSelectedItemPosition() != 0 && materia.getSelectedItemPosition() != 0 &&
                        !(data.getText().toString().equals("Seleziona la data"))){
                    linearInsivibile.setVisibility(View.VISIBLE);
                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        //Setting sul quasi-picker della data
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Calendar calendar = Calendar.getInstance();
                final  SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ITALIAN);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        data.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                        data.setError(null);

                        //Attivazione visibiltà dela lista scelte su data
                        if (aula.getSelectedItemPosition() != 0 && materia.getSelectedItemPosition() != 0 &&
                                !(data.getText().toString().equals("Seleziona la data"))){
                            linearInsivibile.setVisibility(View.VISIBLE);
                        }
                    }

                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        //Setting lista orari
        final Model[] modelItems = new Model[3];
        modelItems[0] = new Model("09:00-10:40", 0);
        modelItems[1] = new Model("11:00-13:30", 0);
        modelItems[2] = new Model("15:00-17:30", 0);

        final CheckboxAdapter adapterPrenota = new CheckboxAdapter(getActivity(), modelItems);

        final ListView lv = (ListView) rootView.findViewById(R.id.listOrari);
        lv.setAdapter(adapterPrenota);

        //Settaggi sul bottone di conferma
        final Button conferma = (Button) rootView.findViewById(R.id.prenotaconfirm);
        conferma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder builder;

                //Impostazione errori
                if (aula.getSelectedItemPosition() == 0 ||
                        materia.getSelectedItemPosition() == 0 ||
                        data.getText().toString().equals("Seleziona la data") ||
                        (modelItems[0].getValue()==0 && modelItems[1].getValue()==0 && modelItems[2].getValue()==0)) {

                    if (aula.getSelectedItemPosition() == 0) {
                        ((TextView)aula.getSelectedView()).setError("Scegli un'aula");
                    }
                    if (materia.getSelectedItemPosition() == 0) {
                        ((TextView)materia.getSelectedView()).setError("Scegli una materia");
                    }
                    if (data.getText().toString().equals("Seleziona la data")) {
                        data.setError("Scegli una data");
                    }
                    if (modelItems[0].getValue()==0 && modelItems[1].getValue()==0 && modelItems[2].getValue()==0){
                        if(!(aula.getSelectedItemPosition() == 0) &&
                                !(materia.getSelectedItemPosition() == 0) &&
                                !(data.getText().toString().equals("Seleziona la data"))) {
                            testoOrari.setError("Seleziona almeno un orario");
                        }
                    }
                } else {
                    //Version check
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
                    }

                    //Impostazione alert dialog
                    builder.setTitle("Prenotare l'aula selezionata?");
                    builder.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast di conferma
                            Toast.makeText(getActivity(), "Aula prenotata", Toast.LENGTH_SHORT).show();

                            //Reset dei dati
                            aula.setAdapter(adapterAula);
                            materia.setAdapter(adapterMateria);
                            data.setText("Seleziona la data");
                            testoOrari.setError(null);

                            for(int i = 0; i < modelItems.length; i++) {
                                modelItems[i].setValue(0);
                            }
                            lv.setAdapter(adapterPrenota);
                            linearInsivibile.setVisibility(View.GONE);

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
