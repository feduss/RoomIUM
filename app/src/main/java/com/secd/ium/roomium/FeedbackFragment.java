package com.secd.ium.roomium;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
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
public class FeedbackFragment extends android.support.v4.app.Fragment {

    EditText editTextProblem;
    EditText primo, secondo, terzo;
    TextView textViewCounterCharacters;

    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_feedback, container, false);

        // Get the Reference of EditText
        editTextProblem = (EditText) rootView.findViewById(R.id.edit_problema);
        textViewCounterCharacters = (TextView) rootView.findViewById(R.id.counter_view);
        primo=(EditText) rootView.findViewById(R.id.primo);
        secondo=(EditText) rootView.findViewById(R.id.secondo);

        // Attach TextWatcher to EditText

        TextWatcher a = new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //textViewCounterCharacters.setText("0/250");
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                textViewCounterCharacters.setText(String.valueOf(s.length()) + "/250");

                if (s.length() >= 250) {
                    textViewCounterCharacters.setText(String.valueOf(s.length()) + "/250");
                    textViewCounterCharacters.setTextColor(Color.parseColor("#FF0080"));
                } else textViewCounterCharacters.setTextColor(Color.parseColor("#000000"));
            }
        };

        editTextProblem.addTextChangedListener(a); //attacco il textchangedlistener all'edit text

        //tasto invio
        Button enter=(Button) rootView.findViewById(R.id.invio);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialog

                if(primo.getText().toString()==null || primo.getText().toString().trim().matches("") ||
                        secondo.getText().toString()==null || secondo.getText().toString().trim().matches("")||
                        editTextProblem.getText().toString()==null || editTextProblem.getText().toString().trim().matches("")){

                    if(primo.getText().toString()==null || primo.getText().toString().trim().matches("")) primo.setError("Riempi il campo ");
                    if(secondo.getText().toString()==null || secondo.getText().toString().trim().matches("")) secondo.setError("Riempi il campo");
                    if(editTextProblem.getText().toString()==null || editTextProblem.getText().toString().trim().matches("")) editTextProblem.setError("Riempi il campo");
                }
                else{
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
                    }
                    builder.setTitle("Invio feedback:")
                            .setMessage("Sei sicuro di voler confermare?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if(editTextProblem!=null) editTextProblem.setText("");
                                    if(primo!=null) primo.setText(null);
                                    if(secondo!=null) secondo.setText(null);
                                    Toast.makeText(getActivity(), "Feedback inviato", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });

        //

        return rootView; //restituisco la vista corrente

    }

}
