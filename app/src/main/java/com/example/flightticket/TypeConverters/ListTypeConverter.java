package com.example.flightticket.TypeConverters;

import androidx.room.TypeConverter;

import com.example.flightticket.DataClasses.Flight;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Converter to allow for storage of Flight lists in the User table
 */
public class ListTypeConverter {
    @TypeConverter
    public List<Flight>  stringToList(String string) {
        Type listType = new TypeToken<List<Flight>>() {}.getType();
        return new Gson().fromJson(string, listType);
    }

    @TypeConverter
    public String listToJson(List<Flight> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
