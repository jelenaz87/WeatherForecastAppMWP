package com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models;


import com.google.gson.annotations.SerializedName;

/**
 * Created by jelena.zivanovic on 12/5/2017.
 */

public class WeatherDetail {

    @SerializedName("id")
    private int weatherId;

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }
}
