package com.example.flightticket.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.flightticket.DataClasses.User;
import com.example.flightticket.TypeConverters.ListTypeConverter;


@Database(entities = {User.class}, version = 2)
@TypeConverters(ListTypeConverter.class)
public abstract class UserDatabase extends RoomDatabase {
    public static final String DB_NAME = "USER_DATABASE";
    public static final String USER_TABLE = "USER_TABLE";

    public abstract UserDAO getUserDAO();

}
