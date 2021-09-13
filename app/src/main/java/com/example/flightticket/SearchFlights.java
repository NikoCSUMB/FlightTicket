package com.example.flightticket;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightticket.API.APIResponseClasses.APIResponse;
import com.example.flightticket.API.RetrofitClient;
import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.utils.FlightsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFlights extends AppCompatActivity {

    private ListView listViewFlights;
    private EditText filterField;

    private FlightsAdapter flightsAdapter;
    private RetrofitClient retrofitClient;
    private List<Flight> flights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights);

        listViewFlights = findViewById(R.id.ListViewFlights);
        filterField = findViewById(R.id.FilterField);

        String country = "US";
        String currency = "USD";
        String locale = "en-US";
        String originPlace = "SFO-sky";
        String destinationPlace = "ORD-sky";
        String outBoundPartialDate = "2021-10-18";
        retrofitClient = RetrofitClient.getClient();
        retrofitClient.getFlightsCall(
                country,
                currency,
                locale,
                originPlace,
                destinationPlace,
                outBoundPartialDate
        ).enqueue(new Callback<>() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    flights = (List<Flight>) response.body().getDataClass(Flight.class);
                    Log.i("Flights", String.valueOf(flights));
                    flightsAdapter = new FlightsAdapter(getApplicationContext(), R.layout.list_item_flight, flights);
                    listViewFlights.setAdapter(flightsAdapter);
                } else {
                    new Exception("Request failed, code: " + response.code()).printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });


        filterField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                flightsAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

    }
}