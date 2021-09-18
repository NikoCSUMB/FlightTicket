package com.example.flightticket.utils;

import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flightticket.R;

import java.util.HashMap;

public class FlightFilterDialog extends FlightDialog {
    private final EditText fromPriceField;
    private final EditText toPriceField;
    private final EditText carrierField;
    private final Button applyFilterBtn;

    public FlightFilterDialog(AppCompatActivity activity){
        flightDialogView = activity.getLayoutInflater().inflate(
                R.layout.dialog_flight_filter,
                activity.findViewById(android.R.id.content),
                false
        );

        fromPriceField = flightDialogView.findViewById(R.id.FromPrice);
        toPriceField = flightDialogView.findViewById(R.id.ToPrice);
        carrierField = flightDialogView.findViewById(R.id.Carrier);
        applyFilterBtn = flightDialogView.findViewById(R.id.ApplyFiltersBtn);

        flightAlertDialog = new AlertDialog.Builder(activity).setView(flightDialogView).create();
    }

    public Button getApplyFilterBtn(){
        return applyFilterBtn;
    }

    public HashMap<String, String> getFilterSettings(){
        return new HashMap<>(){{
            put("fromPrice", fromPriceField.getText().toString());
            put("toPrice", toPriceField.getText().toString());
            put("carrier", carrierField.getText().toString());
        }};
    }
}
