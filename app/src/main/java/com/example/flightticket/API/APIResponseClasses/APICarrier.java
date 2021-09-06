package com.example.flightticket.API.APIResponseClasses;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class APICarrier {
    @SerializedName("CarrierId")
    private int id;

    @SerializedName("Name")
    private String name;

    public APICarrier(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        APICarrier that = (APICarrier) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "\nAPICarrier{" +
                "\nid=" + id +
                ",\n name='" + name + '\'' +
                '}';
    }
}
