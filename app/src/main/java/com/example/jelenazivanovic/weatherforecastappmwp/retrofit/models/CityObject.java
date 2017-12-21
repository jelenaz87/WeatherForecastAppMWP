package com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models;


import com.google.gson.annotations.SerializedName;

/**
 * Created by jelena.zivanovic on 12/5/2017.
 */

public class CityObject {

    @SerializedName("coord")
    private CityCoordinate coordinate;

    public CityCoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(CityCoordinate coordinate) {
        this.coordinate = coordinate;
    }
}
