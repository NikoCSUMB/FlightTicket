package com.example.flightticket.utils;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.R;

public class FlightInfoDialog {
    private final TextView flightInfoRoute;
    private final TextView flightInfoPlaceDep;
    private final TextView flightInfoPlaceDist;
    private final TextView flightInfoCarrier;
    private final TextView flightInfoPrice;

    private final AlertDialog.Builder flightInfoBuilder;
    private final View flightInfoView;

    public FlightInfoDialog(AppCompatActivity activity, Flight flight){
        flightInfoBuilder = new AlertDialog.Builder(activity);
        flightInfoView = activity.getLayoutInflater().inflate(
                R.layout.dialog_flight_info,
                activity.findViewById(android.R.id.content),
                false
        );

        flightInfoRoute = flightInfoView.findViewById(R.id.FlightInfoRoute);
        flightInfoPlaceDep = flightInfoView.findViewById(R.id.FlightInfoPlaceDep);
        flightInfoPlaceDist = flightInfoView.findViewById(R.id.FlightInfoPlaceDist);
        flightInfoCarrier = flightInfoView.findViewById(R.id.FlightInfoCarrier);
        flightInfoPrice = flightInfoView.findViewById(R.id.FlightInfoPrice);

        flightInfoRoute.setText(String.format(
                FlightsAdapter.routeTemplate,
                flight.getPlaceDep().getCityName(),
                flight.getPlaceDist().getCityName()
        ));
        flightInfoPlaceDep.setText(String.format(
                FlightsAdapter.placeTemple,
                flight.getPlaceDep().getAirPortName(),
                flight.getPlaceDep().getCountryName()
        ));
        flightInfoPlaceDist.setText(String.format(
                FlightsAdapter.placeTemple,
                flight.getPlaceDist().getAirPortName(),
                flight.getPlaceDist().getCountryName()
        ));
        flightInfoCarrier.setText(String.format(
                FlightsAdapter.carrierTemplate,
                flight.getCarrier()
        ));
        flightInfoPrice.setText(String.format(
                FlightsAdapter.priceTemplate,
                flight.getMinPrice(),
                flight.getCurrency()
        ));

        flightInfoBuilder.setView(flightInfoView);
        flightInfoBuilder.create();
    }

    public void showDialog(){
        flightInfoBuilder.show();
    }

}
