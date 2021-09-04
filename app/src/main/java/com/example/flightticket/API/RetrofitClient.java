package com.example.flightticket.API;

import static com.example.flightticket.API.API.BASE_URL;

import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.DataClasses.Place;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RetrofitClient {
    private API api;
    private static RetrofitClient retrofitClient;

    private RetrofitClient(){
        OkHttpClient httpClient = new OkHttpClient
                .Builder()
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        api = retrofit.create(API.class);
    }

    public static RetrofitClient getClient() {
        if (retrofitClient == null){
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public Call<Flight> getFlightsCall(
            String country,
            String currency,
            String locale,
            String originplace,
            String destinationplace,
            String outboundpartialdate
    ){
        return api.getFlights(
                country,
                currency,
                locale,
                originplace,
                destinationplace,
                outboundpartialdate
        );
    }

    public Call<List<Place>> getPlacesCall(
            String country,
            String currency,
            String locale,
            String city
    ){
        return api.getPlaces(
                country,
                currency,
                locale,
                city
        );
    }
}
