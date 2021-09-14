package com.example.flightticket;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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

        listViewFlights.setOnItemClickListener((adapterView, view, position, l) -> {
            Flight flight = (Flight) adapterView.getItemAtPosition(position);
            showFlightInfo(flight);
        });

    }

    private void showFlightInfo(Flight flight){
        AlertDialog.Builder flightInfoBuilder = new AlertDialog.Builder(this);
        View flightInfoView = getLayoutInflater().inflate(R.layout.dialog_flight_info, null);

        TextView flightInfoRoute = flightInfoView.findViewById(R.id.FlightInfoRoute);
        TextView flightInfoPlaceDep = flightInfoView.findViewById(R.id.FlightInfoPlaceDep);
        TextView flightInfoPlaceDist = flightInfoView.findViewById(R.id.FlightInfoPlaceDist);
        TextView flightInfoCarrier = flightInfoView.findViewById(R.id.FlightInfoCarrier);
        TextView flightInfoPrice = flightInfoView.findViewById(R.id.FlightInfoPrice);

        flightInfoRoute.setText(String.format(
                FlightsAdapter.routeTemplate,
                flight.getPlaceDep().getCityName(),
                flight.getPlaceDist().getCityName()
        ));
        flightInfoPlaceDep.setText(String.format(
                FlightsAdapter.placeTemple,
                flight.getPlaceDep().getAirPortName(),
                flight.getPlaceDep().getCountryName()
        ));
        flightInfoPlaceDist.setText(String.format(
                FlightsAdapter.placeTemple,
                flight.getPlaceDist().getAirPortName(),
                flight.getPlaceDist().getCountryName()
        ));
        flightInfoCarrier.setText(String.format(
                FlightsAdapter.carrierTemplate,
                flight.getCarrier()
        ));
        flightInfoPrice.setText(String.format(
                FlightsAdapter.priceTemplate,
                flight.getMinPrice(),
                flight.getCurrency()
        ));

//        if(flightInfoView.getParent() != null) {
//            ((ViewGroup)flightInfoView.getParent()).removeView(flightInfoView);
//        }
        flightInfoBuilder.setView(flightInfoView);
        flightInfoBuilder.create();
        flightInfoBuilder.show();
    }
}