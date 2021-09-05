package com.example.flightticket.DataClasses;

import java.util.Objects;

public class Place {

    private int id;
    private String stringId;
    private String airPortName;
    private String cityName;
    private String countryName;

    public Place(int id, String airPortName, String cityName, String countryName) {
        this.id = id;
        this.airPortName = airPortName;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public Place(String stringId, String airPortName, String cityName, String countryName) {
        this.stringId = stringId;
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

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
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
        return getId() == place.getId() && Objects.equals(getStringId(), place.getStringId()) && Objects.equals(getAirPortName(), place.getAirPortName()) && Objects.equals(getCityName(), place.getCityName()) && Objects.equals(getCountryName(), place.getCountryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStringId(), getAirPortName(), getCityName(), getCountryName());
    }

    @Override
    public String toString() {
        return "\nPlace{" +
                "\nid=" + id +
                ",\n stringId='" + stringId + '\'' +
                ",\n airPortName='" + airPortName + '\'' +
                ",\n cityName='" + cityName + '\'' +
                ",\n countryName='" + countryName + '\'' +
                '}';
    }
}
