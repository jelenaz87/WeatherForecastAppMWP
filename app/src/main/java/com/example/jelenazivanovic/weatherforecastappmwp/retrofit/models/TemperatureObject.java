package com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models;


import com.google.gson.annotations.SerializedName;

/**
 * Created by jelena.zivanovic on 12/5/2017.
 */

public class TemperatureObject {

    @SerializedName("temp_max")
    private double maxTemperature;
    @SerializedName("temp_min")
    private double minTemperature;
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("humidity")
    private int humidity;

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
    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

}
