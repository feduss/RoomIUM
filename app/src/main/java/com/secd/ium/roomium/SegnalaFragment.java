package com.secd.ium.roomium;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SegnalaFragment extends android.support.v4.app.Fragment {

    EditText editTextProblem;
    TextView textViewCounterCharacters;

    private ImageView imageHolder;
    private final int requestCode = 20;


    public SegnalaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_segnala, container, false);

        // Get the Reference of EditText
        editTextProblem = (EditText) rootView.findViewById(R.id.edit_problema);
        textViewCounterCharacters = (TextView) rootView.findViewById(R.id.counter_view);

        final int PICK_IMAGE_REQUEST = 1;


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

        editTextProblem.addTextChangedListener(a);


        //immagine
        //CODICE PER L'IMMAGINE DALLA CAMERA O DALLA GALLERIA (continua nell'onCreateResult)
        final Intent intent = new Intent();


        // Show only images, no videos or anything else


        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        Button capturedImageButton = (Button) rootView.findViewById(R.id.bottone_immagine);
        ImageView img= (ImageView) rootView.findViewById(R.id.image1);
        capturedImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(Intent.createChooser(intent, "Seleziona immagine"), PICK_IMAGE_REQUEST);

                //fin qui, apre il menù di selezione file img premendo il tasto. Ancora non la salva nell'image view

            }
        });


        return rootView;

    }



}