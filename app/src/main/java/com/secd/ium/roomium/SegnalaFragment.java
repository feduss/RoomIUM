package com.secd.ium.roomium;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SegnalaFragment extends android.support.v4.app.Fragment {

    EditText editTextProblem;
    EditText aula;
    TextView textViewCounterCharacters;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView imageHolder;
    private final int requestCode = 20;


    ImageView img1=null;
    ImageView img2=null;
    ImageView img3=null;


    public SegnalaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_segnala, container, false);

        // Get the Reference of EditText
        editTextProblem = (EditText) rootView.findViewById(R.id.edit_problema);
        aula = (EditText) rootView.findViewById(R.id.aula);
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

        editTextProblem.addTextChangedListener(a); //attacco il textchangedlistener all'edit text

        //estrapolo il bottone dall'xml
        Button capturedImageButton = (Button) rootView.findViewById(R.id.bottone_immagine);

        //al click sul bottone, fa le seguenti cose (per dirlo banalmente)
        capturedImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //L'intent descrive che cosa vogliamo fare (vedi input del costruttore)
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //se ciò che faccio è selezionare una foto, avvio l'attività della camera
                if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);


                    //Il continuo di questo codice è nell'activity result//
                }
            }
        });

        //tasto invio
        Button enter=(Button) rootView.findViewById(R.id.invio);

        enter.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          //Dialog

                                          AlertDialog.Builder builder;
                                          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                              builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                                          } else {
                                              builder = new AlertDialog.Builder(getContext());
                                          }
                                          builder.setTitle("Invio segnalazione:")
                                                  .setMessage("Sei sicuro di voler confermare?")
                                                  .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                      public void onClick(DialogInterface dialog, int which) {
                                                          if(aula!=null)aula.setText("");
                                                          if(editTextProblem!=null) editTextProblem.setText("");
                                                          Toast.makeText(getActivity(), "Segnalazione inviata", Toast.LENGTH_SHORT).show();
                                                      }
                                                  })
                                                  .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                                      public void onClick(DialogInterface dialog, int which) {
                                                          // do nothing
                                                      }
                                                  })
                                                  .setIcon(android.R.drawable.ic_dialog_alert)
                                                  .show();
                                      }
                                  });

        //

        return rootView; //restituisco la vista corrente

    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            //Estrapolo la bitmap dall'immagine presa dalla camera
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            //Assegno la bitmap all'imageview solo è null

            //WIP: aggiunge la bitmap solo alla prima imageview (devo capire perchè)

            if(img1==null){
                //Estrapolo l'imageview dall'xml
                img1= (ImageView) getView().findViewById(R.id.image1);

                img1.setImageBitmap(imageBitmap);
            }
            else if(img2==null){
                    img2= (ImageView) getView().findViewById(R.id.image2);
                    img2.setImageBitmap(imageBitmap);

            } else if(img3==null){
                        img3= (ImageView) getView().findViewById(R.id.image3);
                        img3.setImageBitmap(imageBitmap);
            }

        }
    }



}