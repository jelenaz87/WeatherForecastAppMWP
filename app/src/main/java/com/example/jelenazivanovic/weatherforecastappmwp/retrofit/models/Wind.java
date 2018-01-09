package com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jelena.zivanovic on 12/13/2017.
 */

public class Wind {

    @SerializedName("speed")
    private float windSpeed;
    @SerializedName("deg")
    private float windDirection;

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(float windDirection) {
        this.windDirection = windDirection;
    }
}
