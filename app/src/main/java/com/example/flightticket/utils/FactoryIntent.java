package com.example.flightticket.utils;

import android.content.Context;
import android.content.Intent;

import com.example.flightticket.HomeActivity;
import com.example.flightticket.LoginActivity;

import java.util.HashMap;

public class FactoryIntent {
    public static Intent getIntent(Class<?> className, Context context) {
        HashMap<Class<?>, Intent> factoryHash =new HashMap<>();

        if(factoryHash.containsKey(className)==false){
            factoryHash.put(className,createIntent(className,context));
        }
        return (factoryHash.get(className));
    }

    public static Intent createIntent(Class<?> className, Context context){
        Intent intent = new Intent(context, className);

        return (intent);
    }


}
