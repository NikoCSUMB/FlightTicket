package com.example.flightticket.API.APIResponseClasses.APIResponseConverters;

import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.DataClasses.Place;

import java.util.List;

@FunctionalInterface
public interface ConvertersFunctionInterface {
    List<?> runConverter();
}
