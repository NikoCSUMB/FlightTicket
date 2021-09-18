package com.example.flightticket.utils;

import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.R;

public class FlightInfoDialog extends FlightDialog{
    private final TextView flightInfoRoute;
    private final TextView flightInfoPlaceDep;
    private final TextView flightInfoPlaceDist;
    private final TextView flightInfoCarrier;
    private final TextView flightInfoPrice;

    Button saveFlightButton;

    public FlightInfoDialog(AppCompatActivity activity, Flight flight){
        flightDialogView = activity.getLayoutInflater().inflate(
                R.layout.dialog_flight_info,
                activity.findViewById(android.R.id.content),
                false
        );

        flightInfoRoute = flightDialogView.findViewById(R.id.FlightInfoRoute);
        flightInfoPlaceDep = flightDialogView.findViewById(R.id.FlightInfoPlaceDep);
        flightInfoPlaceDist = flightDialogView.findViewById(R.id.FlightInfoPlaceDist);
        flightInfoCarrier = flightDialogView.findViewById(R.id.FlightInfoCarrier);
        flightInfoPrice = flightDialogView.findViewById(R.id.FlightInfoPrice);

        saveFlightButton = flightDialogView.findViewById(R.id.SaveFlightBtn);

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

        flightAlertDialog = new AlertDialog.Builder(activity).setView(flightDialogView).create();
    }

    public Button getSaveFlightButton(){
        return saveFlightButton;
    }

}
