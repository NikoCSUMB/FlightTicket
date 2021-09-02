package com.example.flightticket;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testValidate(){
        CreateActivity activity = new CreateActivity();
        List<String> usernames = new ArrayList<>();
        usernames.add("Test");

        // Existing usernames should be rejected
        assertTrue(activity.validate("Yest", "password", usernames));
        assertFalse(activity.validate("Test", "password" ,usernames));

        // Empty fields should be rejected
        assertFalse(activity.validate("Username", "", usernames));
        assertFalse(activity.validate("", "password", usernames));
    }

}