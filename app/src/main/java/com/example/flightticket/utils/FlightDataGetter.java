package com.example.flightticket.utils;

import com.example.flightticket.DataClasses.Flight;

@FunctionalInterface
public interface FlightDataGetter {
    Object getData(Flight flight);
}
