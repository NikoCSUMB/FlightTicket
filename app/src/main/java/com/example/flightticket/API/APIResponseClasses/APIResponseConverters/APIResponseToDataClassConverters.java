package com.example.flightticket.API.APIResponseClasses.APIResponseConverters;

import com.example.flightticket.API.APIResponseClasses.APICarrier;
import com.example.flightticket.API.APIResponseClasses.APICurrency;
import com.example.flightticket.API.APIResponseClasses.APIPlace;
import com.example.flightticket.API.APIResponseClasses.APIQuote;
import com.example.flightticket.API.APIResponseClasses.APIResponse;
import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.DataClasses.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class APIResponseToDataClassConverters {

    private static List<APICarrier> apiCarriers;
    private static List<APICurrency> apiCurrencies;
    private static List<APIPlace> apiPlaces;
    private static List<APIQuote> apiQuotes;

    private static void unpackAPIResponse(APIResponse apiResponse){
        apiCarriers = apiResponse.getCarriers();
        apiCurrencies = apiResponse.getCurrencies();
        apiPlaces = apiResponse.getPlaces();
        apiQuotes = apiResponse.getQuotes();
    }

    public static List<Flight> getFlights(APIResponse apiResponse) {
        unpackAPIResponse(apiResponse);

        List<Flight> flights = new ArrayList<>();
        String currency = apiCurrencies.get(0).getCode();
        for (APIQuote apiQuote : apiQuotes) {
            String id = apiQuote.getId();
            int minPrice = apiQuote.getMinPrice();
            for (Integer carrierId: apiQuote.getOutboundLeg().getCarrierIds()){
                String carrierName = null;
                for (APICarrier apiCarrier: apiCarriers){
                    if (carrierId.equals(apiCarrier.getId())){
                        carrierName = apiCarrier.getName();
                    }
                }
                Place placeDep = null;
                Place placeDist = null;
                for (APIPlace apiPlace: apiPlaces){
                    if (apiPlace.getStringId().equals(apiQuote.getOutboundLeg().getOriginId())){
                        placeDep = parseAPIPlace(apiPlace);
                    }
                    if (apiPlace.getStringId().equals(apiQuote.getOutboundLeg().getDestinationId())){
                        placeDist = parseAPIPlace(apiPlace);
                    }
                    if (placeDep != null && placeDist != null){
                        break;
                    }
                }
                flights.add(new Flight(Integer.parseInt(id), minPrice, carrierName, placeDep, placeDist, currency));
            }
        }
        return flights;
    }

    public static List<Place> getPlaces(APIResponse apiResponse){
        unpackAPIResponse(apiResponse);

        return apiPlaces.stream().map(APIResponseToDataClassConverters::parseAPIPlace).collect(Collectors.toList());
    }

    private static Place parseAPIPlace(APIPlace apiPlace){
        return new Place(apiPlace.getStringId(), apiPlace.getName(), apiPlace.getCityName(), apiPlace.getCountryName());
    }

}
