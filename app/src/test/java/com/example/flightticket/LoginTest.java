package com.example.flightticket;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.room.Room;

import com.example.flightticket.db.UserDAO;
import com.example.flightticket.db.UserDatabase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LoginTest {
    @Test
    public void usernameVal(){
        String notUser = "notRealUsername";
        String realUser = "realUsername";
        List<String> usernames = new ArrayList<>();

        usernames.add(realUser);
        assertFalse(LoginActivity.validateUser(notUser,usernames));
    }


    @Test
    public void passwordVal(){
        String actualPassword = "password";
        String notPassword = "notPassword";
        assertFalse(LoginActivity.validatePassword(actualPassword,notPassword));
    }
}
