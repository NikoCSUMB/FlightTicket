package com.example.flightticket.utils;

import android.content.Context;
import android.content.Intent;

public class FactoryIntent {
    public static Intent getIntent(Class<?> className, Context context) {

        Intent intent = new Intent(context, className);
        return (intent);
    }
}
