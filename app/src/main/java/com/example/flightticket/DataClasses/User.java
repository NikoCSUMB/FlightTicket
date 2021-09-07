package com.example.flightticket.DataClasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.flightticket.db.UserDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User object to be used for creating accounts, logging in, and saving flights.
 */
@Entity(tableName = UserDatabase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int userId;

    private String username;
    private String password;

    private List<Flight> flights;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        flights = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && username.equals(user.username) && password.equals(user.password) && flights.equals(user.flights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, flights);
    }
}
