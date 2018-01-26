package com.secd.ium.roomium;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomePageActivity extends AppCompatActivity {

    Button invio;
    public String enteredText = null;
    public String str=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        invio=(Button) findViewById(R.id.login_docente); //leggo il bottone
        final EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);





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

                //se lo username è vuoto, avvio l'activity dello studente
                if((enteredText==null) || (enteredText.equals(""))) {
                    Intent showResult = new Intent(HomePageActivity.this, AvvisiActivity.class); //Intent che ci fa visualizzare l'activity (chi sta chiamando, l'activity da visualizzare)
                    startActivity(showResult); //mostra a video l'activity

                }
                else{
                    Intent showResult = new Intent(HomePageActivity.this, DocenteActivity.class); //Intent che ci fa visualizzare l'activity (chi sta chiamando, l'activity da visualizzare)
                    EditText inputText = (EditText) findViewById(R.id.username);
                    setUsername(HomePageActivity.this, enteredText);
                    enteredText=null;



                    startActivity(showResult);
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
