package com.example.flightticket;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateAccountTest {
    @Test
    public void validateUsernameTest(){
        List<String> usernames = new ArrayList<>();
        usernames.add("Test");

        // Existing usernames should be rejected
        assertTrue(CreateActivity.validateUser("Yest", usernames));
        assertFalse(CreateActivity.validateUser("Test" ,usernames));
    }

    @Test
    public void validateEmptyTest(){
        // Empty fields should be rejected
        assertFalse(CreateActivity.validateEmpty("Username", ""));
        assertFalse(CreateActivity.validateEmpty("", "password"));
    }
}
