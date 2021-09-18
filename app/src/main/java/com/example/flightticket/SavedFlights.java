package com.example.flightticket;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.flightticket.DB.UserDAO;
import com.example.flightticket.DB.UserDatabase;
import com.example.flightticket.DataClasses.Flight;

import java.util.List;

public class SavedFlights extends AppCompatActivity {
    UserDAO userDAO;
    private FlightListAdapter flightListAdapter;
    private List<Flight> flights;

    SharedPreferences userPref;
    SharedPreferences.Editor userPrefEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_flights);

        TextView welcomeText = findViewById(R.id.userWelcome);

        userPref = getSharedPreferences("userPreferences", MODE_PRIVATE);
        String welcome = "Welcome, " + userPref.getString("username", "");
        welcomeText.setText(welcome);

        String username = userPref.getString("username", "");

        initRecyclerView();
        loadFlightList(username);

    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        flightListAdapter = new FlightListAdapter(this);
        recyclerView.setAdapter(flightListAdapter);
    }

    private void loadFlightList(String username) {
        userDAO = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build().getUserDAO();
        flights = userDAO.getUserByUsername(username).getFlights();
        flightListAdapter.setFlightList(flights);
    }
}



