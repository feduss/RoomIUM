package com.secd.ium.roomium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class SegnalaActivity extends AppCompatActivity {

    EditText editTextProblem;
    TextView textViewCounterCharacters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segnala);


        // Get the Reference of EditText
        editTextProblem=(EditText)findViewById(R.id.edit_problema);
        textViewCounterCharacters=(TextView)findViewById(R.id.counter_view);

        TextWatcher  mTextEditorWatcher = new TextWatcher() {


            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {


                textViewCounterCharacters.setText("0/500");
            }

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                textViewCounterCharacters.setText(String.valueOf(s.length()));
            }

            public void afterTextChanged(Editable s)
            {
                textViewCounterCharacters.setText(String.valueOf(s.length()));

            }
        };

        // Attach TextWatcher to EditText
        editTextProblem.addTextChangedListener(mTextEditorWatcher);

    }



}
