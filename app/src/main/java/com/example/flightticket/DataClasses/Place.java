package com.example.flightticket.DataClasses;

import java.util.Objects;

public class Place {

    private int id;
    private String airPortName;
    private String cityName;
    private String countryName;

    public Place(int id, String airPortName, String cityName, String countryName) {
        this.id = id;
        this.airPortName = airPortName;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirPortName() {
        return airPortName;
    }

    public void setAirPortName(String airPortName) {
        this.airPortName = airPortName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return getId() == place.getId() && Objects.equals(getAirPortName(), place.getAirPortName()) && Objects.equals(getCityName(), place.getCityName()) && Objects.equals(getCountryName(), place.getCountryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAirPortName(), getCityName(), getCountryName());
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", airPortName='" + airPortName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
