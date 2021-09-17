package com.example.flightticket.utils;

import android.view.View;

import androidx.appcompat.app.AlertDialog;

public abstract class FlightDialog {
    protected AlertDialog flightAlertDialog;
    protected View flightDialogView;

    public void showDialog(){
        flightAlertDialog.show();
    }
    public void hideDialog(){
        flightAlertDialog.dismiss();
    }
}
