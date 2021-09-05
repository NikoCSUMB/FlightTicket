package com.example.flightticket;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateAccountTest {
    @Test
    public void validateUsernameTest(){
        CreateActivity activity = new CreateActivity();
        List<String> usernames = new ArrayList<>();
        usernames.add("Test");

        // Existing usernames should be rejected
        assertTrue(activity.validateUser("Yest", usernames));
        assertFalse(activity.validateUser("Test" ,usernames));
    }

    @Test
    public void validateEmptyTest(){
        CreateActivity activity = new CreateActivity();

        // Empty fields should be rejected
        assertFalse(activity.validateEmpty("Username", ""));
        assertFalse(activity.validateEmpty("", "password"));
    }
}
