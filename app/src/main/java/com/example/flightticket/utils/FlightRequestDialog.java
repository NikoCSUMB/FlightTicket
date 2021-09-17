package com.example.flightticket.utils;

import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flightticket.R;

import java.util.HashMap;

public class FlightRequestDialog extends FlightDialog {
    private final EditText countryField;
    private final EditText currencyField;
    private final EditText destinationPlaceField;
    private final EditText localeField;
    private final EditText originPlaceField;
    private final EditText outBoundPartialDateField;
    private final Button sendRequestBtn;

    public FlightRequestDialog(AppCompatActivity activity){
        flightDialogView = activity.getLayoutInflater().inflate(
                R.layout.dialog_flight_request,
                activity.findViewById(android.R.id.content),
                false
        );
        countryField = flightDialogView.findViewById(R.id.Country);
        currencyField = flightDialogView.findViewById(R.id.Currency);
        destinationPlaceField = flightDialogView.findViewById(R.id.DestinationPlace);
        localeField = flightDialogView.findViewById(R.id.Locale);
        originPlaceField = flightDialogView.findViewById(R.id.OriginPlace);
        outBoundPartialDateField = flightDialogView.findViewById(R.id.OutBoundPartialDate);
        sendRequestBtn = flightDialogView.findViewById(R.id.SendRequestBtn);

        flightAlertDialog = new AlertDialog.Builder(activity).setView(flightDialogView).create();
    }

    public Button getSendRequestBtn(){
        return sendRequestBtn;
    }

    public HashMap<String, String> getRequestParameters(){
        return new HashMap<>(){{
            put("country", countryField.getText().toString());
            put("currency", currencyField.getText().toString());
            put("destinationPlace", destinationPlaceField.getText().toString());
            put("locale", localeField.getText().toString());
            put("originPlace", originPlaceField.getText().toString());
            put("outBoundPartialDate", outBoundPartialDateField.getText().toString());
        }};
    }
}
