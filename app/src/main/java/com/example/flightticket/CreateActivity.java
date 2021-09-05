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
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                if (!validateUser(username, usernames)) {
                    Toast.makeText(getApplicationContext(), "That username is already taken!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!validateEmpty(username, password)) {
                    Toast.makeText(getApplicationContext(), "Both fields must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                // insert user and return to main activity


            }
        });
    }

    // Searches the list to see if the current username already exists.
    public boolean validateUser(String username, List<String> names){
        for (String name : names){
            if (username.equals(name)){
                return false;
            }
        }
        return true;
    }

//     Checks if both fields are filled
    public boolean validateEmpty(String username, String password) {
        return username.length() != 0 && password.length() != 0;
    }

}