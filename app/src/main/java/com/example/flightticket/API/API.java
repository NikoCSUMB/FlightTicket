package com.example.flightticket.API;

import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.DataClasses.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    String HOST_HEADER = "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";
    String KEY = "628df7c2edmshd3cb6e4c4df5e0ep11590ejsn27fc3c9fa38a";
    String CONTENT_TYPE = "application/json";

    String BASE_URL = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/";

    @GET("browseroutes/v1.0/{country}/{currency}/{locale}/{originplace}/{destinationplace}/{outboundpartialdate}")
    Call<Flight> getFlights(
            @Path("country") String country,
            @Path("currency") String currency,
            @Path("locale") String locale,
            @Path("originplace") String originplace,
            @Path("destinationplace") String destinationplace,
            @Path("outboundpartialdate") String outboundpartialdate
    );

    @GET("autosuggest/v1.0/{country}/{currency}/{locale}")
    Call<List<Place>> getPlaces(
            @Path("country") String country,
            @Path("currency") String currency,
            @Path("locale") String locale,
            @Query("query") String city
    );

}
