package com.example.flightticket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences userPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button signUpButton = (Button) findViewById(R.id.signUpButton);

        // Check if user is already logged in and take them to the home page
        userPref = getSharedPreferences("userPreferences", MODE_PRIVATE);
        if (!userPref.getString("username", "").equals("")) {
            Intent homeIntent = new Intent(LandingActivity.this, HomeActivity.class);
            startActivity(homeIntent);
        }

        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:
                Intent loginIntent = new Intent(LandingActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.signUpButton:
                Intent createIntent = new Intent(LandingActivity.this, CreateActivity.class);
                startActivity(createIntent);
                break;
        }

    }
}