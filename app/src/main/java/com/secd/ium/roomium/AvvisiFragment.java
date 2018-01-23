package com.secd.ium.roomium;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AvvisiFragment extends ListFragment {


    public AvvisiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup                  container, Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_avvisi, container, false);
       /* ListView listView = (ListView)fragmentLayout.findViewById(R.id.lista);
        //ListView listView1 = (ListView) fragmentLayout.findViewById(R.id.lista);
        String[] array= {"Sospensione delle lezioni", "Pinna ha pubblicato i voti", "Inagibilità del Palazzo delle Scienze",
                "Il ricevimento di Prof. Fenu è annullato", "Possibilità di tirocinio presso Ferrero"};

        List<String> array1= new ArrayList();

        for(int i=0; i<array.length; i++){
            array1.add(array[i]);
        }

        //quello dopo r.layout è il design generico con 2 righe
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.riga_lista, array1);

        listView.setAdapter(adapter);
*/
        return fragmentLayout;


    }
}

