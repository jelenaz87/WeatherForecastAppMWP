package com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jelena.zivanovic on 12/13/2017.
 */

public class Wind {

    @SerializedName("speed")
    private double windSpeed;
    @SerializedName("deg")
    private double windDirection;

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }
}
