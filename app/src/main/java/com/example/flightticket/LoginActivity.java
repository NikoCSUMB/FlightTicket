package com.example.flightticket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flightticket.DataClasses.User;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.flightticket.db.UserDAO;
import com.example.flightticket.db.UserDatabase;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    UserDAO userDAO;
    static List<String> usernames;

    EditText loginEmail;
    EditText loginPassword;

    Button loginBtn;

    String emailInput;
    String passwordInput;

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);


        userDAO = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build().getUserDAO();

        usernames = userDAO.getUsernames();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailInput = loginEmail.getText().toString();
                passwordInput = loginPassword.getText().toString();
                User loginUser = userDAO.getUserByUsername(emailInput);
                if (validateUser(emailInput,usernames)&&validatePassword(passwordInput,loginUser.getPassword())){
                    Intent i= new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "INVALID USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public static Boolean validateUser(String username,List<String> usernames){
        for (String name : usernames) {
            if (username.equals(name)) {
                return true;
            }
        }
        Log.i("ERROR","User");
        return false;
    }
    public static Boolean validatePassword(String password, String actualPassword){

        return actualPassword.equals(password);
    }




}
