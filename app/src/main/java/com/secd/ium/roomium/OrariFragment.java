package com.secd.ium.roomium;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrariFragment extends android.support.v4.app.Fragment{


    public OrariFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_orari, container, false);

        Spinner corso = (Spinner) rootView.findViewById(R.id.spinnercorso);
        String[] itemsCorso = new String[]{"Seleziona il Corso di Laurea","Informatica", "Informatica - Magistrale", "Matematica", "Fisica"};
        ArrayAdapter<String> adapterCorso = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsCorso);
        corso.setAdapter(adapterCorso);

        Spinner annoCorso = (Spinner) rootView.findViewById(R.id.spinnerannoCorso);
        String[] itemsAnnoCorso = new String[]{"Seleziona l'anno di corso","1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapterAnnoCorso = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsAnnoCorso);
        annoCorso.setAdapter(adapterAnnoCorso);

        Button data = (Button) rootView.findViewById(R.id.datebutton);
        Button conferma = (Button) rootView.findViewById(R.id.orariconfirm);

        data.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getFragmentManager(), "datePicker");
            }
        });

        conferma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent showOrari = new Intent(getActivity(), TabellaOrariActivity.class);
                startActivity(showOrari);
            }
        });
        return rootView;
    }
}