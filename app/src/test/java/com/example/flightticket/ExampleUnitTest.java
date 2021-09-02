package com.example.flightticket;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.flightticket.API.RetrofitClient;
import com.example.flightticket.DataClasses.Flight;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void getFlightsCallAPITest() {
        RetrofitClient retrofit = RetrofitClient.getClient();
        retrofit.getFlightsCall(
                "US",
                "USD",
                "en-US",
                "SFO-sky",
                "ORD-sky",
                "2021-10-18"
        ).enqueue(new Callback<Flight>() {
            @Override
            public void onResponse(Call<Flight> call, Response<Flight> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Flight flight = response.body();
//                  TODO: Test flight contents
                } else {
                    new Exception("Request failed, code: " + response.code()).printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Flight> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }
}