package com.example.flightticket.utils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flightticket.R;

import java.util.HashMap;

public class FlightFilterDialog {
    private EditText fromPriceField;
    private EditText toPriceField;
    private EditText carrierField;
    private Button applyFilterBtn;

    private AlertDialog.Builder flightFilterBuilder;
    private View flightFilterView;

    public FlightFilterDialog(AppCompatActivity activity){
        flightFilterBuilder = new AlertDialog.Builder(activity);
        flightFilterView = activity.getLayoutInflater().inflate(
                R.layout.dialog_flight_filter,
                activity.findViewById(android.R.id.content),
                false
        );

        fromPriceField = flightFilterView.findViewById(R.id.FromPrice);
        toPriceField = flightFilterView.findViewById(R.id.ToPrice);
        carrierField = flightFilterView.findViewById(R.id.Carrier);
        applyFilterBtn = flightFilterView.findViewById(R.id.ApplyFiltersBtn);

        flightFilterBuilder.setView(flightFilterView);
        flightFilterBuilder.create();
    }

    public Button getApplyFilterBtn(){
        return applyFilterBtn;
    }

    public void showDialog(){
        flightFilterBuilder.show();
    }

    public HashMap<String, String> getFilterSettings(){
        return new HashMap<>(){{
            put("fromPrice", fromPriceField.getText().toString());
            put("toPrice", toPriceField.getText().toString());
            put("carrier", carrierField.getText().toString());
        }};
    }
}
