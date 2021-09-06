package com.example.flightticket.API.APIResponseClasses;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class APIPlace {
    @SerializedName("PlaceId")
    private String stringId;

    @SerializedName("IataCode")
    private String iataCode;

    @SerializedName(value = "Name", alternate = {"PlaceName"})
    private String name;

    @SerializedName("Type")
    private String type;

    @SerializedName("SkyscannerCode")
    private String skyscannerCode;

    @SerializedName("CityName")
    private String cityName;

    @SerializedName("CityId")
    private String cityId;

    @SerializedName("CountryName")
    private String countryName;

    public APIPlace(
            String stringId,
            String iataCode,
            String name,
            String type,
            String skyscannerCode,
            String cityName,
            String cityId,
            String countryName
    ) {
        this.stringId = stringId;
        this.iataCode = iataCode;
        this.name = name;
        this.type = type;
        this.skyscannerCode = skyscannerCode;
        this.cityName = cityName;
        this.cityId = cityId;
        this.countryName = countryName;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSkyscannerCode() {
        return skyscannerCode;
    }

    public void setSkyscannerCode(String skyscannerCode) {
        this.skyscannerCode = skyscannerCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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
        APIPlace apiPlace = (APIPlace) o;
        return Objects.equals(getStringId(), apiPlace.getStringId())
                && Objects.equals(getIataCode(), apiPlace.getIataCode())
                && Objects.equals(getName(), apiPlace.getName())
                && Objects.equals(getType(), apiPlace.getType())
                && Objects.equals(getSkyscannerCode(), apiPlace.getSkyscannerCode())
                && Objects.equals(getCityName(), apiPlace.getCityName())
                && Objects.equals(getCityId(), apiPlace.getCityId())
                && Objects.equals(getCountryName(), apiPlace.getCountryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getStringId(),
                getIataCode(),
                getName(),
                getType(),
                getSkyscannerCode(),
                getCityName(),
                getCityId(),
                getCountryName());
    }

    @Override
    public String toString() {
        return "\nAPIPlace{" +
                "\n stringId='" + stringId + '\'' +
                ",\n iataCode='" + iataCode + '\'' +
                ",\n name='" + name + '\'' +
                ",\n type='" + type + '\'' +
                ",\n skyscannerCode='" + skyscannerCode + '\'' +
                ",\n cityName='" + cityName + '\'' +
                ",\n cityId='" + cityId + '\'' +
                ",\n countryName='" + countryName + '\'' +
                '}';
    }
}
