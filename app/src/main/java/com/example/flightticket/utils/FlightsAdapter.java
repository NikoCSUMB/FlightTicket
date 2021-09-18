package com.example.flightticket.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.R;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FlightsAdapter extends BaseAdapter {

    public static final String routeTemplate = "%s -> %s";
    public static final String priceTemplate = "Price: %s%s";
    public static final String carrierTemplate = "Airline: %s";
    public static final String placeTemple = "Airport: %s\nCountry: %s";
    private static final String defaultFromPrice = "0";
    private static final String defaultToPrice = String.valueOf(Integer.MAX_VALUE);
    private final int flightLayout;
    private final Context context;
    private final List<Flight> initialFlights;
    private List<Flight> flights;

    public FlightsAdapter(Context context, int flightLayout, List<Flight> flights) {
        this.flightLayout = flightLayout;
        this.context = context;
        this.flights = flights;
        this.initialFlights = flights;
    }

    public List<Flight> getFilteredFlights(){
        return this.flights;
    }

    @Override
    public int getCount() {
        return flights.size();
    }

    @Override
    public Flight getItem(int position) {
        return flights.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            view = vi.inflate(flightLayout, null);
        }

        Flight flight = getItem(position);

        if (flight != null) {
            TextView itemRouteField = view.findViewById(R.id.Route);
            TextView itemDetailsField = view.findViewById(R.id.Details);

            if (itemRouteField != null) {
                itemRouteField.setText(
                    String.format(
                        FlightsAdapter.routeTemplate,
                        flight.getPlaceDep().getCityName(),
                        flight.getPlaceDist().getCityName()
                    )
                );
            }

            if (itemDetailsField != null) {
                itemDetailsField.setText(String.format(FlightsAdapter.priceTemplate, flight.getMinPrice(), flight.getCurrency()));
            }
        }

        return view;
    }

    public void filterFlights(HashMap<String, String> filterSettings){
        Integer fromPrice = Integer.parseInt(
                Objects.requireNonNull(
                        filterSettings.get("fromPrice")
                ).isEmpty() ? defaultFromPrice : Objects.requireNonNull(filterSettings.get("fromPrice"))
        );
        Integer toPrice = Integer.parseInt(
                Objects.requireNonNull(
                        filterSettings.get("toPrice")
                ).isEmpty() ? defaultToPrice : Objects.requireNonNull(filterSettings.get("toPrice"))
        );
        String carrier = filterSettings.get("carrier");
        flights = initialFlights.stream()
                .filter(
                        flight -> (
                                flight.getMinPrice() >= fromPrice && flight.getMinPrice() <= toPrice
                        ) || flight.getCarrier().equals(carrier)
                ).collect(Collectors.toList());
        notifyDataSetChanged();
    }
}
