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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Recupero la vista principale del layout
        View fragmentLayout = inflater.inflate(R.layout.fragment_avvisi, container, false);

        //Recupero la lista dall'id dell'xml
       ListView listView = (ListView)fragmentLayout.findViewById(android.R.id.list);

        //Array di stringhe degli avvisi
        String[] array= {"Sospensione delle lezioni", "Pinna ha pubblicato i voti", "Inagibilità del Palazzo delle Scienze",
                "Il ricevimento di Prof. Fenu è annullato", "Possibilità di tirocinio presso Ferrero"};

        //Arraylist che contiene gli elementi della listview
        ArrayList<String> array1= new ArrayList();

        for(int i=0; i<array.length; i++){
            array1.add(array[i]);
        }

        //Costruisco l'adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.riga_lista, R.id.titolo_riga, array1);

        //questa riga è problematica...da fixare! (da errore un errore del genere, nel debugger:
        //"AppCompatTextView cannot be cast to android.view.ViewGroup"
        //---> listView.setAdapter(adapter); <---//

        return fragmentLayout; //il return è corretto, non cambiarlo


    }
}

