package com.secd.ium.roomium;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocenteFragment extends android.support.v4.app.Fragment {

    TextView datiDoc;

    public DocenteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup                  container, Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.activity_docente, container, false);

        View rootView1 = inflater.inflate(R.layout.fragment_docente, container, false);

        datiDoc= (TextView) rootView1.findViewById(R.id.nome_cognome_doc);


        String username=getUsername(getContext());

        if(username!=null){
            if(datiDoc!=null)datiDoc.setText(username);
        }

        return rootView1;
    }

    public static String getUsername(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        return prefs.getString("username", "");

    }

}
