package com.example.flightticket.API.APIResponseClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class APIOutboundLeg {
    @SerializedName("CarrierIds")
    private List<Integer> carrierIds;

    @SerializedName("OriginId")
    private String originId;

    @SerializedName("DestinationId")
    private String destinationId;

    @SerializedName("DepartureDate")
    private String departureDate;

    public APIOutboundLeg(List<Integer> carrierIds, String originId, String destinationId, String departureDate) {
        this.carrierIds = carrierIds;
        this.originId = originId;
        this.destinationId = destinationId;
        this.departureDate = departureDate;
    }

    public List<Integer> getCarrierIds() {
        return carrierIds;
    }

    public void setCarrierIds(List<Integer> carrierIds) {
        this.carrierIds = carrierIds;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        APIOutboundLeg that = (APIOutboundLeg) o;
        return getOriginId().equals(that.getOriginId())
                && getDestinationId().equals(that.getDestinationId())
                && Objects.equals(getCarrierIds(), that.getCarrierIds())
                && Objects.equals(getDepartureDate(), that.getDepartureDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getCarrierIds(),
                getOriginId(),
                getDestinationId(),
                getDepartureDate());
    }

    @Override
    public String toString() {
        return "\nAPIOutboundLeg{" +
                "\ncarrierIds=" + carrierIds +
                ",\n originId=" + originId +
                ",\n destinationId=" + destinationId +
                ",\n departureDate='" + departureDate + '\'' +
                '}';
    }
}
