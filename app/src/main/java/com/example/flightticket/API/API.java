package com.example.flightticket.API;

import com.example.flightticket.API.APIResponseClasses.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    String BASE_URL = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/";

//  Common Headers
    String HOST_HEADER = "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";
    String KEY_HEADER = "628df7c2edmshd3cb6e4c4df5e0ep11590ejsn27fc3c9fa38a";

    @GET("browseroutes/v1.0/{country}/{currency}/{locale}/{originPlace}/{destinationPlace}/{outBoundPartialDate}")
    Call<APIResponse> getFlights(
            @Path("country") String country,
            @Path("currency") String currency,
            @Path("locale") String locale,
            @Path("originPlace") String originPlace,
            @Path("destinationPlace") String destinationPlace,
            @Path("outBoundPartialDate") String outBoundPartialDate
    );


    @GET("autosuggest/v1.0/{country}/{currency}/{locale}/")
    Call<APIResponse> getPlaces(
            @Path("country") String country,
            @Path("currency") String currency,
            @Path("locale") String locale,
            @Query("query") String city
    );

}
