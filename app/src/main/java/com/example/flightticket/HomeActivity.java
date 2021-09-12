package com.example.flightticket;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button searchFlightsButton = (Button) findViewById(R.id.searchFlightsButton);
        Button savedFlightsButton = (Button) findViewById(R.id.savedFlightsButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        searchFlightsButton.setOnClickListener(this);
        savedFlightsButton.setOnClickListener(this);

        // android is throwing warnings about the switch case format so I'm just gonna use this
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(HomeActivity.this, LandingActivity.class);
                startActivity(logoutIntent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchFlightsButton:
                Intent loginIntent = new Intent(HomeActivity.this, SearchFlights.class);
                startActivity(loginIntent);
                break;
            case R.id.savedFlightsButton:
                Intent createIntent = new Intent(HomeActivity.this, SavedFlights.class);
                startActivity(createIntent);
                break;
        }
    }

}