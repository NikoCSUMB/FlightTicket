package com.example.flightticket.API;

import static com.example.flightticket.API.API.BASE_URL;

import com.example.flightticket.API.APIResponseClasses.APIResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public Call<APIResponse> getFlightsCall(
            String country,
            String currency,
            String locale,
            String originPlace,
            String destinationPlace,
            String outBoundPartialDate
    ){
        return api.getFlights(
                country,
                currency,
                locale,
                originPlace,
                destinationPlace,
                outBoundPartialDate
        );
    }

    public Call<APIResponse> getPlacesCall(
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
