package com.secd.ium.roomium;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends android.support.v4.app.Fragment {


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_info, container, false);

        Button logoutButton= (Button) rootView.findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showResult = new Intent(getActivity(), HomePageActivity.class); //Intent che ci fa visualizzare l'activity (chi sta chiamando, l'activity da visualizzare)
                startActivity(showResult); //mostra a video l'activity
            }
        });

        return rootView;


    }

}
