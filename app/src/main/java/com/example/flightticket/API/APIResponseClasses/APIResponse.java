package com.example.flightticket.API.APIResponseClasses;

import com.example.flightticket.API.APIResponseClasses.APIResponseConverters.APIResponseToDataClassConverters;
import com.example.flightticket.API.APIResponseClasses.APIResponseConverters.ConvertersFunctionInterface;
import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.DataClasses.Place;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class APIResponse {

    @SerializedName("Quotes")
    private List<APIQuote> quotes;
    @SerializedName("Places")
    private List<APIPlace> places;
    @SerializedName("Carriers")
    private List<APICarrier> carriers;
    @SerializedName("Currencies")
    private List<APICurrency> currencies;

    private APIResponse(
            List<APIQuote> quotes,
            List<APIPlace> places,
            List<APICarrier> carriers,
            List<APICurrency> currencies) {
        this.quotes = quotes;
        this.places = places;
        this.carriers = carriers;
        this.currencies = currencies;
    }

    public List<?> getDataClass(Class<?> dataClass){
        APIResponse apiResponse = this;
        return Objects.requireNonNull(new HashMap<Class<?>, ConvertersFunctionInterface>() {{
            put(Flight.class, () -> APIResponseToDataClassConverters.getFlights(apiResponse));
            put(Place.class, () -> APIResponseToDataClassConverters.getPlaces(apiResponse));
        }}.get(dataClass)).runConverter();
    }

    public List<APIQuote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<APIQuote> quotes) {
        this.quotes = quotes;
    }

    public List<APIPlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<APIPlace> places) {
        this.places = places;
    }

    public List<APICarrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<APICarrier> carriers) {
        this.carriers = carriers;
    }

    public List<APICurrency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<APICurrency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        APIResponse that = (APIResponse) o;
        return Objects.equals(getQuotes(), that.getQuotes())
                && Objects.equals(getPlaces(), that.getPlaces())
                && Objects.equals(getCarriers(), that.getCarriers())
                && Objects.equals(getCurrencies(), that.getCurrencies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getQuotes(),
                getPlaces(),
                getCarriers(),
                getCurrencies());
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "quotes=" + quotes +
                ",\n places=" + places +
                ",\n carriers=" + carriers +
                ",\n currencies=" + currencies +
                '}';
    }
}
