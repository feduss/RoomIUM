package com.secd.ium.roomium;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomePageActivity extends AppCompatActivity {

    Button invio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        invio=(Button) findViewById(R.id.login_docente); //leggo il bottone

        invio.setOnClickListener(new View.OnClickListener(){ //al click, fa questo
            @Override
            public void onClick(View v){

                    Intent showResult= new Intent(HomePageActivity.this, AvvisiActivity.class); //Intent che ci fa visualizzare l'activity (chi sta chiamando, l'activity da visualizzare)

                    //showResult.putExtra("Result", person); //
                    startActivity(showResult); //mostra a video l'activity

            }

        });

        EditText password = (EditText) findViewById(R.id.password);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());

    }



}
