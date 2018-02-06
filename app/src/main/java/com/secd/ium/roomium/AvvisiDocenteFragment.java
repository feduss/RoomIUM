package com.secd.ium.roomium;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvvisiDocenteFragment extends android.support.v4.app.Fragment {

    public AvvisiDocenteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup                  container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_avvisi_docente, container, false);

        final EditText titolo = (EditText)rootView.findViewById(R.id.editTitolo);
        final EditText avviso = (EditText)rootView.findViewById(R.id.editAvviso);
        final TextView contoCaratteri = (TextView)rootView.findViewById(R.id.counterView);
        Button invio = (Button)rootView.findViewById(R.id.buttonInvio);

        TextWatcher watch = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                contoCaratteri.setText(String.valueOf(s.length()) + "/120");

                if(s.length() <= 120) {
                    contoCaratteri.setTextColor(Color.parseColor("#000000"));
                } else {
                    contoCaratteri.setTextColor(Color.parseColor("#FF0080"));
                }
            }
        };
        avviso.addTextChangedListener(watch);

        invio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(titolo.getText().toString() == null
                        || titolo.getText().toString().equals("")
                        || avviso.getText().toString() == null
                        || avviso.getText().toString().equals("")) {

                    if(titolo.getText().toString() == null
                            || titolo.getText().toString().equals("")) {
                        titolo.setError("Riempi il campo");
                    }

                    if(avviso.getText().toString() == null
                            || avviso.getText().toString().equals("")) {
                        avviso.setError("Riempi il campo");
                    }

                } else {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
                    }

                    builder.setTitle("Inserire il seguente avviso?");
                    builder.setMessage(titolo.getText() + "\n-----\n" + avviso.getText());
                    builder.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            titolo.setText("");
                            avviso.setText("");
                            contoCaratteri.setText("");

                            Toast.makeText(getActivity(), "Avviso inviato", Toast.LENGTH_SHORT).show();
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
