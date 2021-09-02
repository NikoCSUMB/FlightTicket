package com.example.flightticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.flightticket.API.RetrofitClient;
import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.DataClasses.Place;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
//import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RetrofitClient retrofit = RetrofitClient.getClient();
//        retrofit.getFlightsCall(
//                "US",
//                "USD",
//                "en-US",
//                "SFO-sky",
//                "ORD-sky",
//                "2021-10-18"
//        ).enqueue(new Callback<Flight>() {
//            @Override
//            public void onResponse(Call<Flight> call, Response<Flight> response) {
//                if (response.isSuccessful()) {
//                    assert response.body() != null;
//                    Flight flight = response.body();
//                    System.out.println(flight);
//                } else {
//                    new Exception("Request failed, code: " + response.code()).printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Flight> call, Throwable t) {
//                try {
//                    throw t;
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//            }
//        });
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/SFO-sky/ORD-sky/2021-10-18")
//                .get()
//                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
//                .addHeader("x-rapidapi-key", "48f6d3fb17mshec5ab8ed60076abp15278ejsn5975046520b0")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            Log.i("Response", response.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        RetrofitClient retrofit = RetrofitClient.getClient();
        retrofit.getPlacesCall(
                "UK",
                "GBP",
                "en-GB",
                "Stockholm"
        ).enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.i("Body", String.valueOf(response.body()));
                } else {
                    new Exception("Request failed, code: " + response.code()).printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

}