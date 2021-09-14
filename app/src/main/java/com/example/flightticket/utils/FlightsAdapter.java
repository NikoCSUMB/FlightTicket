package com.example.flightticket.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FlightsAdapter extends BaseAdapter implements Filterable {

    public static final String routeTemplate = "%s -> %s";
    public static final String priceTemplate = "Price: %s%s";
    public static final String carrierTemplate = "Airline: %s";
    public static final String placeTemple = "Airport: %s\nCountry: %s";



    private static final HashMap<String, FlightDataGetter> flightDataGetters = new HashMap<>() {{
        put("minprice", Flight::getMinPrice);
        put("carrier", Flight::getCarrier);
        put("placedep", Flight::getPlaceDep);
        put("placedist", Flight::getPlaceDist);
        put("currency", Flight::getCurrency);
    }};

    private int flightLayout;
    private Context context;
    private List<Flight> flights;
    private List<Flight> initialFlights;

    public FlightsAdapter(Context context, int flightLayout, List<Flight> flights) {
        this.flightLayout = flightLayout;
        this.context = context;
        this.flights = flights;
        this.initialFlights = flights;
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

    @SuppressWarnings("unchecked")
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSeqFilter) {
                FilterResults filterResults = new FilterResults();
                List<Flight> filteredFlights = new ArrayList<>();
                String strFilter = charSeqFilter.toString().toLowerCase();
                Map<String, String> filterSettings;
                try {
                    filterSettings = (HashMap<String, String>)
                        Arrays.stream(strFilter.split(","))
                        .map(s -> s.split(":"))
                        .collect(Collectors.toMap(key -> key[0], val -> val[1]));
                    Log.i("FilterSettings", String.valueOf(filterSettings));
                } catch (ArrayIndexOutOfBoundsException e){
                    Log.i("Filter", "Incorrect filter format");
                    filterResults.values = initialFlights;
                    filterResults.count = initialFlights.size();
                    return filterResults;
                }

                filterSettings.forEach((key, value) -> {
                    Log.i("FilterSettingsIter", key + " | " + value);
                    Log.i("FilterDataGettersHasFunction?", String.valueOf(FlightsAdapter.flightDataGetters.containsKey(key)));
                    if (FlightsAdapter.flightDataGetters.containsKey(key)) {
                        filteredFlights.addAll(initialFlights.stream().filter(
                            flight -> value.equals(String.valueOf(
                                (Objects.requireNonNull(FlightsAdapter.flightDataGetters.get(key))).getData(flight))
                            )
                        ).collect(Collectors.toList()));
                    }
                });
                Log.i("FilterFilteredFlights", String.valueOf(filteredFlights));
                filterResults.values = filteredFlights;
                filterResults.count = filteredFlights.size();

                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                flights = (List<Flight>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
