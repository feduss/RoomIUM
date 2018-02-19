package com.secd.ium.roomium;


import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrariFragment extends android.support.v4.app.Fragment{
    SharedPreferences prefs;
    final String KEY_SavedSel1 = "Saved Selection1";
    final String KEY_SavedSel2 = "Saved Selection2";


    public OrariFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_orari, container, false);

        final Spinner corso = (Spinner) rootView.findViewById(R.id.spinnercorso);
        String[] itemsCorso = new String[]{"Seleziona il Corso di Laurea","Informatica", "Informatica - Magistrale", "Matematica", "Fisica"};
        ArrayAdapter<String> adapterCorso = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsCorso);
        corso.setAdapter(adapterCorso);

        final Spinner annoCorso = (Spinner) rootView.findViewById(R.id.spinnerannoCorso);
        String[] itemsAnnoCorso = new String[]{"Seleziona l'anno di corso","1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapterAnnoCorso = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsAnnoCorso);
        annoCorso.setAdapter(adapterAnnoCorso);

        //Button data = (Button) rootView.findViewById(R.id.datebutton);
        final TextView data = (TextView) rootView.findViewById(R.id.datepicker);
        Button conferma = (Button) rootView.findViewById(R.id.orariconfirm);

        prefs = getActivity().getPreferences(MODE_PRIVATE);
        int prefsInt1 = prefs.getInt(KEY_SavedSel1, -1);
        int prefsInt2 = prefs.getInt(KEY_SavedSel2, -1);
        if (prefsInt1 != -1) corso.setSelection(prefsInt1);
        if (prefsInt2 != -1) annoCorso.setSelection(prefsInt2);

        data.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Calendar calendar = Calendar.getInstance();
                final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ITALIAN);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        data.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                        data.setError(null);

                    }

                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        conferma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (corso.getSelectedItemPosition() == 0 || annoCorso.getSelectedItemPosition() == 0) {
                    if (corso.getSelectedItemPosition() == 0) ((TextView)corso.getSelectedView()).setError("Scegli un corso");
                    if (annoCorso.getSelectedItemPosition() == 0) ((TextView)annoCorso.getSelectedView()).setError("Scegli un anno");
                    if (data.getText().toString().equals("Seleziona la data")) data.setError("Scegli una data");
                } else {
                    Intent showOrari = new Intent(getActivity(), TabellaOrariActivity.class);
                    startActivity(showOrari);
                }
            }
        });

        corso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SharedPreferences.Editor editor = getActivity().getPreferences(MODE_PRIVATE).edit();
                editor.putInt(KEY_SavedSel1, position);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        annoCorso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SharedPreferences.Editor editor = getActivity().getPreferences(MODE_PRIVATE).edit();
                editor.putInt(KEY_SavedSel2, position);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        return rootView;
    }
}