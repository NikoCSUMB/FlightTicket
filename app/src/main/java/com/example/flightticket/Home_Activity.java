package com.example.flightticket;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Home_Activity extends {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home;

        Button savedFlightsButton = findViewById(R.id.savedFlightsButton);
        Button searchFlightsButton = findViewById(R.id.searchFlightButton)
        TextView username = findViewById(R.id.textViewusername);
        TextView userId = findViewById(R.id.textViewUserId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.intentFactory(.this);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.intentFactory(.this);
                startActivity(intent);
            }
        });
    }

}
