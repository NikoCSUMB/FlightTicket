package com.example.flightticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flightticket.DataClasses.User;
import com.example.flightticket.db.UserDAO;
import com.example.flightticket.db.UserDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * This activity allows the user to create an account with their desired username and password
 */
public class CreateActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private Button createButton;

    UserDAO userDAO;
    List<String> usernames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        usernameText = findViewById(R.id.usernameEdit);
        passwordText = findViewById(R.id.passwordEdit);
        createButton = findViewById(R.id.createAccountButton);

        userDAO = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build().getUserDAO();

        usernames = userDAO.getUsernames();


        // Validation begins once the button is pressed
        // If both checks pass then the user is inserted into the database
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

                User newUser = new User(username, password);
                userDAO.insert(newUser);
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent); // TODO: replace with proper intent factory

            }
        });
    }

    /**
     * Searches the list to see if the current username already exists.
     */
    public boolean validateUser(String username, List<String> names){
        for (String name : names){
            if (username.equals(name)){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if both input fields are filled
     */
    public boolean validateEmpty(String username, String password) {
        return username.length() != 0 && password.length() != 0;
    }

}