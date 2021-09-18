package com.example.flightticket;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.flightticket.DB.UserDAO;
import com.example.flightticket.DB.UserDatabase;
import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.DataClasses.User;
import com.example.flightticket.utils.FlightFilterDialog;
import com.example.flightticket.utils.FlightInfoDialog;
import com.example.flightticket.utils.FlightsAdapter;

import java.util.List;

public class SavedFlights extends AppCompatActivity {

    private static final String DELETE_BTN_TEXT = "Delete Flight";

    private TextView title;
    private ListView flightsListView;
    private Button filterFlightsBtn;

    private FlightFilterDialog flightFilterDialog;
    private FlightInfoDialog flightInfoDialog;
    private FlightsAdapter flightsAdapter;
    private List<Flight> savedFlights;

    private UserDAO userDAO;
    private User user;
    private SharedPreferences userPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_flights);

        title = findViewById(R.id.TitleView);
        flightsListView = findViewById(R.id.SavedFlightsView);
        filterFlightsBtn = findViewById(R.id.FilterFlightsBtn);

        userDAO = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build().getUserDAO();

        userPref = getSharedPreferences("userPreferences", MODE_PRIVATE);
        user = userDAO.getUserByUsername(userPref.getString("username", ""));

        savedFlights = user.getFlights();
        flightsAdapter = new FlightsAdapter(getApplicationContext(), R.layout.list_item_flight, savedFlights);
        flightsListView.setAdapter(flightsAdapter);

        filterFlightsBtn.setOnClickListener(view -> {
            flightFilterDialog = new FlightFilterDialog(SavedFlights.this);
            flightFilterDialog.getApplyFilterBtn().setOnClickListener(viewFilter -> {
                flightsAdapter.filterFlights(flightFilterDialog.getFilterSettings());
                flightFilterDialog.hideDialog();
            });
            flightFilterDialog.showDialog();
        });

        flightsListView.setOnItemClickListener((adapterView, view, position, l) -> {
            Flight flight = (Flight) adapterView.getItemAtPosition(position);
            flightInfoDialog = new FlightInfoDialog(SavedFlights.this, flight);
            flightInfoDialog.getSaveFlightButton().setText(DELETE_BTN_TEXT);
            flightInfoDialog.getSaveFlightButton().setOnClickListener(viewFlightInfoDialog -> {
                user.removeFlight(flight);
                userDAO.update(user);
                flightsAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Flight deleted!", Toast.LENGTH_SHORT).show();
            });
            flightInfoDialog.showDialog();
        });
    }
}



