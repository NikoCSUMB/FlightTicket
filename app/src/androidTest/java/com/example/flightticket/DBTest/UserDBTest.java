package com.example.flightticket.DBTest;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.flightticket.DataClasses.User;
import com.example.flightticket.db.UserDAO;
import com.example.flightticket.db.UserDatabase;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class UserDBTest {

    /**
     * Tests basic functions of the UserDAO (insert, select, etc)
     */
    @Test
    public void userDAOTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        UserDatabase userDatabase = Room.databaseBuilder(appContext, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();

        userDatabase.clearAllTables();
        UserDAO userDAO = userDatabase.getUserDAO();

        List<User> users = userDAO.getUsers();
        assertEquals(users.size(), 0);

        User user = new User("testuser", "testpass");

        userDAO.insert(user);
        user.setUserId(userDAO.getUserByUsername("testuser").getUserId());
        assertEquals(userDAO.getUserByUsername("testuser"), user);
        assertEquals(userDAO.getUserById(user.getUserId()), user);

        userDAO.delete(user);
        assertNull(userDAO.getUserByUsername("testuser"));
    }
}
