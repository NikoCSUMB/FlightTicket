package com.example.flightticket;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button signUpButton = (Button) findViewById(R.id.signUpButton);

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