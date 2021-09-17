package com.example.flightticket.utils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flightticket.R;

import java.util.HashMap;

public class FlightRequestDialog {
    private Button sendRequestBtn;
    private EditText countryField;
    private EditText currencyField;
    private EditText destinationPlaceField;
    private EditText localeField;
    private EditText originPlaceField;
    private EditText outBoundPartialDateField;

    private AlertDialog.Builder flightRequestBuilder;
    private View flightRequestView;

    public FlightRequestDialog(AppCompatActivity activity){
        flightRequestBuilder = new AlertDialog.Builder(activity);
        flightRequestView = activity.getLayoutInflater().inflate(
                R.layout.dialog_flight_request,
                activity.findViewById(android.R.id.content),
                false
        );

        countryField = flightRequestView.findViewById(R.id.Country);
        currencyField = flightRequestView.findViewById(R.id.Currency);
        destinationPlaceField = flightRequestView.findViewById(R.id.DestinationPlace);
        localeField = flightRequestView.findViewById(R.id.Locale);
        originPlaceField = flightRequestView.findViewById(R.id.OriginPlace);
        outBoundPartialDateField = flightRequestView.findViewById(R.id.OutBoundPartialDate);
        sendRequestBtn = flightRequestView.findViewById(R.id.SendRequestBtn);

        flightRequestBuilder.setView(flightRequestView);
        flightRequestBuilder.create();
    }

    public Button getSendRequestBtn(){
        return sendRequestBtn;
    }

    public void showDialog(){
        flightRequestBuilder.show();
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
