package com.example.flightticket;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightticket.API.APIResponseClasses.APIResponse;
import com.example.flightticket.API.RetrofitClient;
import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.utils.FlightFilterDialog;
import com.example.flightticket.utils.FlightInfoDialog;
import com.example.flightticket.utils.FlightRequestDialog;
import com.example.flightticket.utils.FlightsAdapter;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFlights extends AppCompatActivity {

    private ListView listViewFlights;
    private Button sendRequestDialogBtn;
    private Button setFilterDialogBtn;

    private FlightRequestDialog flightRequestDialog;
    private FlightFilterDialog flightFilterDialog;
    private FlightsAdapter flightsAdapter;
    private RetrofitClient retrofitClient;
    private List<Flight> flights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights);

        listViewFlights = findViewById(R.id.ListViewFlights);
        sendRequestDialogBtn = findViewById(R.id.SendRequestDialogBtn);
        setFilterDialogBtn = findViewById(R.id.SetFilterDialogBtn);

        retrofitClient = RetrofitClient.getClient();

        listViewFlights.setOnItemClickListener((adapterView, view, position, l) -> {
            Flight flight = (Flight) adapterView.getItemAtPosition(position);
            FlightInfoDialog flightInfoDialog = new FlightInfoDialog(SearchFlights.this, flight);
            flightInfoDialog.showDialog();
        });

        sendRequestDialogBtn.setOnClickListener(view -> {
            flightRequestDialog = new FlightRequestDialog(SearchFlights.this);
            flightRequestDialog.getSendRequestBtn().setOnClickListener(viewSendRequest -> {
                HashMap<String, String> requestParams = flightRequestDialog.getRequestParameters();
                retrofitClient.getFlightsCall(
                    requestParams.get("country"),
                    requestParams.get("currency"),
                    requestParams.get("locale"),
                    requestParams.get("originPlace"),
                    requestParams.get("destinationPlace"),
                    requestParams.get("outBoundPartialDate")
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
                flightRequestDialog.hideDialog();
            });
            flightRequestDialog.showDialog();
        });

        setFilterDialogBtn.setOnClickListener(view -> {
            flightFilterDialog = new FlightFilterDialog(SearchFlights.this);
            flightFilterDialog.getApplyFilterBtn().setOnClickListener(viewFilter -> {
                flightsAdapter.filterFlights(flightFilterDialog.getFilterSettings());
                flightFilterDialog.hideDialog();
            });
            flightFilterDialog.showDialog();
        });
    }
}