package com.example.flightticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private Button createButton;

    List<String> usernames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        usernameText = findViewById(R.id.usernameEdit);
        passwordText = findViewById(R.id.passwordEdit);
        createButton = findViewById(R.id.createAccountButton);

        // Temporary list for testing purposes. Will be replaced with UserDAO
        usernames = new ArrayList<>();
        usernames.add("Test");
        usernames.add("Meow");

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if validate(), add account & go to next activity
                if(validate(usernameText.getText().toString(), passwordText.getText().toString(), usernames)){

                }

            }
        });
    }

    public boolean validate(String username, String password, List<String> names){
        // Search db for existing username
        for (String name : names){
            if (username.equals(name)){
               Toast.makeText(this, "That username is already taken!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
         // Check for empty fields
        if (username.length() == 0 || password.length() == 0){
            Toast.makeText(this, "Both fields must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}