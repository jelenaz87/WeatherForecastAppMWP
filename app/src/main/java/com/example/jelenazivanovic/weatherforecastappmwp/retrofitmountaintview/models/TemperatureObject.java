package com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models;


import com.google.gson.annotations.SerializedName;

/**
 * Created by jelena.zivanovic on 12/5/2017.
 */

public class TemperatureObject {

    @SerializedName("max")
    private double maxTemperature;
    @SerializedName("min")
    private double minTemperature;


    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }


}
