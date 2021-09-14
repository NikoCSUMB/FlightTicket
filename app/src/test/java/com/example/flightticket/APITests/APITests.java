package com.example.flightticket.APITests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import com.example.flightticket.API.APIResponseClasses.APIResponse;
import com.example.flightticket.API.RetrofitClient;
import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.DataClasses.Place;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class APITests {
    @Test
    public void getFlightsCallAPITest() throws IOException {
        String country = "US";
        String currency = "USD";
        String locale = "en-US";
        String originPlace = "SFO-sky";
        String destinationPlace = "ORD-sky";
        String outBoundPartialDate = "2021-10-18";

        RetrofitClient retrofit = RetrofitClient.getClient();
        APIResponse apiResponse = retrofit.getFlightsCall(
            country,
            currency,
            locale,
            originPlace,
            destinationPlace,
            outBoundPartialDate
        ).execute().body();

        assertNotNull(apiResponse);
        assertFalse(apiResponse.getQuotes().isEmpty());
        assertFalse(apiResponse.getPlaces().isEmpty());
        assertFalse(apiResponse.getCarriers().isEmpty());
        assertFalse(apiResponse.getCurrencies().isEmpty());
    }

    @Test
    public void getPlacesCallAPITest() throws IOException {
        String country = "UK";
        String currency = "GBP";
        String locale = "en-GB";
        String city = "Stockholm";

        RetrofitClient retrofit = RetrofitClient.getClient();
        APIResponse apiResponse = retrofit.getPlacesCall(
            country,
            currency,
            locale,
            city
        ).execute().body();

        assertNotNull(apiResponse);
        assertNull (apiResponse.getQuotes());
        assertFalse(apiResponse.getPlaces().isEmpty());
        assertNull (apiResponse.getCarriers());
        assertNull (apiResponse.getCurrencies());
    }

    @Test
    public void parseAPIResponseFlightDataClassConverterTest() throws IOException {
        String country = "US";
        String currency = "USD";
        String locale = "en-US";
        String originPlace = "SFO-sky";
        String destinationPlace = "ORD-sky";
        String outBoundPartialDate = "2021-10-18";

        RetrofitClient retrofit = RetrofitClient.getClient();
        APIResponse apiResponse = retrofit.getFlightsCall(
                country,
                currency,
                locale,
                originPlace,
                destinationPlace,
                outBoundPartialDate
        ).execute().body();

        assertNotNull(apiResponse);
        List<?> unCastFlights = apiResponse.getDataClass(Flight.class);
        List<Flight> flights = new ArrayList<>();
        for (Object unCastFlight: unCastFlights) {
            if (unCastFlight instanceof Flight){
                flights.add((Flight) unCastFlight);
            } else {
                fail("ERROR: Could not cast APIResponse object to list of Flight objects");
            }
        }
        assertNotNull(flights);
        assertFalse(flights.isEmpty());
        for (Flight flight: flights) {
            assertNotNull(flight.getMinPrice());
            assertNotNull(flight.getCarrier());
            assertNotNull(flight.getPlaceDep());
            assertNotNull(flight.getPlaceDist());
            assertNotNull(flight.getCurrency());
        }
    }

    @Test
    public void parseAPIResponsePlaceDataClassConverterTest() throws IOException {
        String country = "UK";
        String currency = "GBP";
        String locale = "en-GB";
        String city = "Stockholm";

        RetrofitClient retrofit = RetrofitClient.getClient();
        APIResponse apiResponse = retrofit.getPlacesCall(country, currency, locale, city).execute().body();

        assertNotNull(apiResponse);
        List<?> unCastPlaces = apiResponse.getDataClass(Place.class);
        List<Place> places = new ArrayList<>();
        for (Object unCastPlace: unCastPlaces) {
            if (unCastPlace instanceof Place){
                places.add((Place) unCastPlace);
            } else {
                fail("ERROR: Could not cast APIResponse object to list of Flight objects");
            }
        }
        assertNotNull(places);
        assertFalse(places.isEmpty());
        for (Place place: places) {
            assertNotNull(place.getStringId());
            assertNotNull(place.getAirPortName());
            assertNull(place.getCityName());
            assertNotNull(place.getCountryName());
        }
    }
}