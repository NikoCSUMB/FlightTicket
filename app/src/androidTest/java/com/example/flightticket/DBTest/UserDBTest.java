package com.example.flightticket.DBTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.flightticket.DB.UserDAO;
import com.example.flightticket.DB.UserDatabase;
import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.DataClasses.Place;
import com.example.flightticket.DataClasses.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class UserDBTest {

    private static final String TEST_USERNAME = "testuser";
    private static final String TEST_PASSWORD = "testpass";

    private UserDatabase userDatabase;
    private UserDAO userDAO;
    private User user;
    private Context appContext;

    @Before
    public void DBSetup(){
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        userDatabase = Room.databaseBuilder(appContext, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();
        userDatabase.clearAllTables();
        userDAO = userDatabase.getUserDAO();
        List<User> users = userDAO.getUsers();
        assertEquals(users.size(), 0);
        user = new User("testuser", "testpass");
        userDAO.insert(user);
        user.setUserId(userDAO.getUserByUsername("testuser").getUserId());
    }

    @Test
    public void userDAOTest() {
        assertEquals(userDAO.getUserByUsername("testuser"), user);
        assertEquals(userDAO.getUserById(user.getUserId()), user);
    }

    @Test
    public void userSaveFlightTest(){
        Place dummyPlace = new Place(0, "SomeAirPortName", "SomeCityName", "SomeCountryName");
        Flight flight = new Flight(0, 50, "SomeCarrier", dummyPlace, dummyPlace, "USD");
        user.insertFlight(flight);
        userDAO.update(user);
        assertEquals(flight, userDAO.getUserByUsername("testuser").getFlights().get(0));
    }

    @After
    public void DBClean(){
        userDAO.delete(user);
        assertNull(userDAO.getUserByUsername("testuser"));
        userDatabase.clearAllTables();
    }
}
