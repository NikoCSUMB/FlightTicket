package com.example.flightticket.API.APIResponseClasses;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class APICurrency {
    @SerializedName("Code")
    private String code;

    @SerializedName("Symbol")
    private String symbol;

    @SerializedName("ThousandsSeparator")
    private String thousandsSeparator;

    @SerializedName("DecimalSeparator")
    private String decimalSeparator;

    @SerializedName("SymbolOnLeft")
    private boolean symbolOnLeft;

    @SerializedName("SpaceBetweenAmountAndSymbol")
    private boolean spaceBetweenAmountAndSymbol;

    @SerializedName("RoundingCoefficient")
    private int roundingCoefficient;

    @SerializedName("DecimalDigits")
    private int decimalDigits;

    public APICurrency(
            String code,
            String symbol,
            String thousandsSeparator,
            String decimalSeparator,
            boolean symbolOnLeft,
            boolean spaceBetweenAmountAndSymbol,
            int roundingCoefficient,
            int decimalDigits) {
        this.code = code;
        this.symbol = symbol;
        this.thousandsSeparator = thousandsSeparator;
        this.decimalSeparator = decimalSeparator;
        this.symbolOnLeft = symbolOnLeft;
        this.spaceBetweenAmountAndSymbol = spaceBetweenAmountAndSymbol;
        this.roundingCoefficient = roundingCoefficient;
        this.decimalDigits = decimalDigits;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getThousandsSeparator() {
        return thousandsSeparator;
    }

    public void setThousandsSeparator(String thousandsSeparator) {
        this.thousandsSeparator = thousandsSeparator;
    }

    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    public void setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    public boolean isSymbolOnLeft() {
        return symbolOnLeft;
    }

    public void setSymbolOnLeft(boolean symbolOnLeft) {
        this.symbolOnLeft = symbolOnLeft;
    }

    public boolean isSpaceBetweenAmountAndSymbol() {
        return spaceBetweenAmountAndSymbol;
    }

    public void setSpaceBetweenAmountAndSymbol(boolean spaceBetweenAmountAndSymbol) {
        this.spaceBetweenAmountAndSymbol = spaceBetweenAmountAndSymbol;
    }

    public int getRoundingCoefficient() {
        return roundingCoefficient;
    }

    public void setRoundingCoefficient(int roundingCoefficient) {
        this.roundingCoefficient = roundingCoefficient;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        APICurrency that = (APICurrency) o;
        return isSymbolOnLeft() == that.isSymbolOnLeft()
                && isSpaceBetweenAmountAndSymbol() == that.isSpaceBetweenAmountAndSymbol()
                && getRoundingCoefficient() == that.getRoundingCoefficient()
                && getDecimalDigits() == that.getDecimalDigits()
                && Objects.equals(getCode(), that.getCode())
                && Objects.equals(getSymbol(), that.getSymbol())
                && Objects.equals(getThousandsSeparator(), that.getThousandsSeparator())
                && Objects.equals(getDecimalSeparator(), that.getDecimalSeparator());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getCode(),
                getSymbol(),
                getThousandsSeparator(),
                getDecimalSeparator(),
                isSymbolOnLeft(),
                isSpaceBetweenAmountAndSymbol(),
                getRoundingCoefficient(),
                getDecimalDigits());
    }

    @Override
    public String toString() {
        return "\nAPICurrency{" +
                "\ncode='" + code + '\'' +
                ",\n symbol='" + symbol + '\'' +
                ",\n thousandsSeparator='" + thousandsSeparator + '\'' +
                ",\n decimalSeparator='" + decimalSeparator + '\'' +
                ",\n symbolOnLeft=" + symbolOnLeft +
                ",\n spaceBetweenAmountAndSymbol=" + spaceBetweenAmountAndSymbol +
                ",\n roundingCoefficient=" + roundingCoefficient +
                ",\n decimalDigits=" + decimalDigits +
                '}';
    }
}
