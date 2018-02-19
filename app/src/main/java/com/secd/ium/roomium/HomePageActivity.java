package com.secd.ium.roomium;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageActivity extends AppCompatActivity {

    Button invio;
    public String enteredText = null;
    public String str=null;

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Premi ancora INDIETRO per uscire dall'app", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        invio=(Button) findViewById(R.id.login_docente); //leggo il bottone
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);





        TextWatcher a = new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                enteredText = username.getText().toString();

            }
        };

        username.addTextChangedListener(a); //attacco il textchangedlistener all'edit text


        invio.setOnClickListener(new View.OnClickListener(){ //al click, fa questo
            @Override
            public void onClick(View v){

                if((enteredText==null || enteredText.equals("")) && (password.getText().toString()==null || password.getText().toString().equals(""))) {
                    username.setError("Lo username non può essere vuoto!");
                    password.setError("La password non può essere vuota!");
                }
                else if((enteredText==null || enteredText.equals("")) && (password.getText().toString()!=null)) {
                    username.setError("Lo username non può essere vuoto!");
                }
                //se lo username è vuoto, avvio l'activity dello studente
                    else if((enteredText.equals("studente") && (password.getText().toString()==null || password.getText().toString().equals("")))) {
                        Intent showResult = new Intent(HomePageActivity.this, AvvisiActivity.class); //Intent che ci fa visualizzare l'activity (chi sta chiamando, l'activity da visualizzare)
                        startActivity(showResult); //mostra a video l'activity
                        finish();

                    }
                    else if((enteredText.equals("studente")&&(password.getText().toString()!=null))){
                        password.setError("Se sei uno studente, c'è scritto che devi lasciare vuoto il campo della password..");
                    }
                        else{
                            Intent showResult = new Intent(HomePageActivity.this, DocenteActivity.class); //Intent che ci fa visualizzare l'activity (chi sta chiamando, l'activity da visualizzare)
                            EditText inputText = (EditText) findViewById(R.id.username);
                            setUsername(HomePageActivity.this, enteredText);

                            //dopo il login, rimuovo il testo da tutti i campi non più usati
                            enteredText=null;
                            username.setText(null);
                            password.setText(null);



                            startActivity(showResult);
                            finish();
                        }

            }

        });


        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());

    }

    //salvo le preferenze per lo username
    public static void setUsername(Context context, String username) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", username);
        editor.commit();
    }



}
