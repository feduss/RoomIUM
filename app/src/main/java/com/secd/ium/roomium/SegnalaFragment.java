package com.secd.ium.roomium;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;
    private int PROFILE_PIC_COUNT=0;


    final CharSequence[] items = {"Fai una foto", "Scegli dalla galleria", "Annulla"};


    ImageView img1 = null;
    ImageView img2 = null;
    ImageView img3 = null;

    ImageButton remove1 = null;
    ImageButton remove2 = null;
    ImageButton remove3 = null;


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
            public void onClick(View view) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
                    builder.setTitle("Aggiungi una foto:");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {

                            if (items[item].equals("Fai una foto")) {
                                PROFILE_PIC_COUNT = 1;

                                requestPermissions(
                                        new String[]{Manifest.permission.CAMERA},
                                        2000);

                                if(ActivityCompat.checkSelfPermission(getActivity(),
                                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                    startActivityForResult(intent, REQUEST_CAMERA);
                                }


                            } else if (items[item].equals("Scegli dalla galleria")) {
                                PROFILE_PIC_COUNT = 1;


                                requestPermissions(
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                        2000);

                                if(ActivityCompat.checkSelfPermission(getActivity(),
                                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                                    Intent intent = new Intent(
                                            Intent.ACTION_PICK,
                                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                                    startActivityForResult(intent, SELECT_FILE);
                                }
                            } else if (items[item].equals("Annulla")) {
                                PROFILE_PIC_COUNT = 0;
                                dialog.dismiss();
                            }
                        }
                    });
                    builder.show();
                }
            });


        remove1 = (ImageButton) rootView.findViewById(R.id.remove1);
        remove2 = (ImageButton) rootView.findViewById(R.id.remove2);
        remove3 = (ImageButton) rootView.findViewById(R.id.remove3);

        remove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img1.setImageBitmap(null);
                img1 = null;
                remove1.setVisibility(View.INVISIBLE);

            }
        });

        remove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img2.setImageBitmap(null);
                img2 = null;
                remove2.setVisibility(View.INVISIBLE);

            }
        });

        remove3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img3.setImageBitmap(null);
                img3 = null;
                remove3.setVisibility(View.INVISIBLE);

            }
        });

        //tasto invio
        Button enter = (Button) rootView.findViewById(R.id.invio);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialog

                if (aula.getText().toString() == null || aula.getText().toString().trim().matches("") ||
                        editTextProblem.getText().toString() == null || editTextProblem.getText().toString().trim().matches("")) {

                    if (aula.getText().toString() == null || aula.getText().toString().trim().matches(""))
                        aula.setError("Riempi il campo");
                    if (editTextProblem.getText().toString() == null || editTextProblem.getText().toString().trim().matches(""))
                        editTextProblem.setError("Riempi il campo");
                } else {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
                    }
                    builder.setTitle("Invio segnalazione:")
                            .setMessage("Sei sicuro di voler confermare?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (aula != null) aula.setText(null);
                                    if (editTextProblem != null) editTextProblem.setText(null);
                                    img1.setImageBitmap(null);
                                    img2.setImageBitmap(null);
                                    img3.setImageBitmap(null);
                                    remove1.setVisibility(View.INVISIBLE);
                                    remove2.setVisibility(View.INVISIBLE);
                                    remove3.setVisibility(View.INVISIBLE);

                                    Toast.makeText(getActivity(), "Segnalazione inviata", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();

            //Estrapolo la bitmap dall'immagine presa dalla camera
            Bitmap imageBitmap = (Bitmap) extras.get("data");


            if(img1==null){
                //Estrapolo l'imageview dall'xml
                img1= (ImageView) getView().findViewById(R.id.image1);

                img1.setImageBitmap(imageBitmap);

                remove1.setVisibility(View.VISIBLE);
            }
            else if(img2==null){
                img2= (ImageView) getView().findViewById(R.id.image2);
                img2.setImageBitmap(imageBitmap);

                remove2.setVisibility(View.VISIBLE);

            } else if(img3==null){
                img3= (ImageView) getView().findViewById(R.id.image3);
                img3.setImageBitmap(imageBitmap);
                remove3.setVisibility(View.VISIBLE);
            }

        }
        else {
            if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
                if (requestCode == 2) {
                    Uri returnUri = data.getData();
                    Bitmap bitmapImage = null;
                    try {
                        bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (img1 == null) {
                        //Estrapolo l'imageview dall'xml
                        img1 = (ImageView) getView().findViewById(R.id.image1);

                        img1.setImageBitmap(bitmapImage);

                        remove1.setVisibility(View.VISIBLE);
                    } else if (img2 == null) {
                        img2 = (ImageView) getView().findViewById(R.id.image2);
                        img2.setImageBitmap(bitmapImage);

                        remove2.setVisibility(View.VISIBLE);

                    } else if (img3 == null) {
                        img3 = (ImageView) getView().findViewById(R.id.image3);
                        img3.setImageBitmap(bitmapImage);
                        remove3.setVisibility(View.VISIBLE);
                    }
                }
            }
        }


        }

}