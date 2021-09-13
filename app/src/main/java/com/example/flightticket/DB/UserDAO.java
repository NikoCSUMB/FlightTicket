package com.example.flightticket.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.flightticket.DataClasses.User;

import java.util.List;

/**
 * UserDAO used to access and manipulate the UserDatabase
 */
@Dao
public interface UserDAO {

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);

    @Query("SELECT * FROM " + UserDatabase.USER_TABLE + " ORDER BY userId ASC")
    List<User> getUsers();

    @Query("SELECT username FROM " + UserDatabase.USER_TABLE + " ORDER BY username ASC")
    List<String> getUsernames();

    @Query("SELECT * FROM " + UserDatabase.USER_TABLE + " WHERE userId = :id")
    User getUserById(int id);

    @Query("SELECT * FROM " + UserDatabase.USER_TABLE + " WHERE username = :username")
    User getUserByUsername(String username);



}
