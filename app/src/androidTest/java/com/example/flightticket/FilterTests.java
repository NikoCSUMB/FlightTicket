package com.example.flightticket;

import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.DataClasses.Place;
import com.example.flightticket.utils.FlightsAdapter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class FilterTests {
    private static final String DEFAULT_FROM_PRICE = "0";
    private static final String DEFAULT_TO_PRICE = String.valueOf(Integer.MAX_VALUE);
    private static final Place DUMMY_PLACE = new Place(0, "SomeAirPortName", "SomeCityName", "SomeCountryName");
    private static final List<Flight> FLIGHTS_TO_FILTER = new ArrayList<>(){{
        add(new Flight(0, 50, "SomeCarrier33", DUMMY_PLACE, DUMMY_PLACE, "USD"));
        add(new Flight(0, 90, "SomeCarrier", DUMMY_PLACE, DUMMY_PLACE, "USD"));
        add(new Flight(0, 14, "SomeCarrier2", DUMMY_PLACE, DUMMY_PLACE, "USD"));
        add(new Flight(0, 25, "SomeCarrier1", DUMMY_PLACE, DUMMY_PLACE, "USD"));
        add(new Flight(0, 52352, "SomeCarrier", DUMMY_PLACE, DUMMY_PLACE, "USD"));
        add(new Flight(0, 5110, "SomeCarrier", DUMMY_PLACE, DUMMY_PLACE, "USD"));
        add(new Flight(0, -1234, "SomeCarrier", DUMMY_PLACE, DUMMY_PLACE, "USD"));
        add(new Flight(0, 0, "SomeCarrier3", DUMMY_PLACE, DUMMY_PLACE, "USD"));
    }};

    @Test
    public void BetweenPriceTest() {
        HashMap<String, String> filterSettings = new HashMap<>(){{
            put("fromPrice", "40");
            put("toPrice", "90");
            put("carrier", "SomeCarrier");
        }};
        checkFilter(filterSettings);
    }

    @Test
    public void NoToPriceTest(){
        HashMap<String, String> filterSettings = new HashMap<>(){{
            put("fromPrice", "40");
            put("toPrice", "");
            put("carrier", "SomeCarrier");
        }};
        checkFilter(filterSettings);
    }

    @Test
    public void NoFromPriceTest(){
        HashMap<String, String> filterSettings = new HashMap<>(){{
            put("fromPrice", "");
            put("toPrice", "90");
            put("carrier", "SomeCarrier");
        }};
        checkFilter(filterSettings);
    }

    @Test
    public void NoCarrierTest(){
        HashMap<String, String> filterSettings = new HashMap<>(){{
            put("fromPrice", "40");
            put("toPrice", "90");
            put("carrier", "");
        }};
        checkFilter(filterSettings);
    }

    private void checkFilter(HashMap<String, String> filterSettings){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FlightsAdapter flightsAdapter = new FlightsAdapter(appContext, 0, FLIGHTS_TO_FILTER);
        flightsAdapter.filterFlights(filterSettings);
        Log.i("FilterSettings", String.valueOf(filterSettings));
        Log.i("FilteredFlights", String.valueOf(flightsAdapter.getFilteredFlights()));
        Integer fromPrice = Integer.parseInt(
                Objects.requireNonNull(
                        filterSettings.get("fromPrice")
                ).isEmpty() ? DEFAULT_FROM_PRICE : Objects.requireNonNull(filterSettings.get("fromPrice"))
        );
        Integer toPrice = Integer.parseInt(
                Objects.requireNonNull(
                        filterSettings.get("toPrice")
                ).isEmpty() ? DEFAULT_TO_PRICE : Objects.requireNonNull(filterSettings.get("toPrice"))
        );
        flightsAdapter.getFilteredFlights().forEach(
            flight -> assertTrue(
                flight.getCarrier().equals(filterSettings.get("carrier")) || (
                            flight.getMinPrice() <= toPrice
                            &&
                            flight.getMinPrice() >= fromPrice
                        )
            )
        );
    }
}