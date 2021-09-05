package com.example.flightticket.API.APIResponseClasses;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class APIQuote {
    @SerializedName("QuoteId")
    private String id;

    @SerializedName("MinPrice")
    private int minPrice;

    @SerializedName("Direct")
    private boolean direct;

    @SerializedName("OutboundLeg")
    private APIOutboundLeg outboundLeg;

    @SerializedName("QuoteDateTime")
    private String quoteDateTime;

    public APIQuote(String id, int minPrice, boolean direct, APIOutboundLeg outboundLeg, String quoteDateTime) {
        this.id = id;
        this.minPrice = minPrice;
        this.direct = direct;
        this.outboundLeg = outboundLeg;
        this.quoteDateTime = quoteDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public boolean isDirect() {
        return direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }

    public APIOutboundLeg getOutboundLeg() {
        return outboundLeg;
    }

    public void setOutboundLeg(APIOutboundLeg outboundLeg) {
        this.outboundLeg = outboundLeg;
    }

    public String getQuoteDateTime() {
        return quoteDateTime;
    }

    public void setQuoteDateTime(String quoteDateTime) {
        this.quoteDateTime = quoteDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        APIQuote apiQuote = (APIQuote) o;
        return getId().equals(apiQuote.getId())
                && getMinPrice() == apiQuote.getMinPrice()
                && isDirect() == apiQuote.isDirect()
                && Objects.equals(getOutboundLeg(), apiQuote.getOutboundLeg())
                && Objects.equals(getQuoteDateTime(), apiQuote.getQuoteDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getMinPrice(),
                isDirect(),
                getOutboundLeg(),
                getQuoteDateTime());
    }

    @Override
    public String toString() {
        return "\nAPIQuote{" +
                "\nid=" + id +
                ",\n minPrice=" + minPrice +
                ",\n direct=" + direct +
                ",\n outboundLeg=" + outboundLeg +
                ",\n quoteDateTime='" + quoteDateTime + '\'' +
                '}';
    }
}
