package com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models;


import com.google.gson.annotations.SerializedName;

/**
 * Created by jelena.zivanovic on 12/5/2017.
 */

public class CityCoordinate {

    @SerializedName("lat")
   private double cityLatitude;

    @SerializedName("lon")
   private double cityLongitude;


    public double getCityLatitude() {
        return cityLatitude;
    }

    public void setCityLatitude(double cityLatitude) {
        this.cityLatitude = cityLatitude;
    }

    public double getCityLongitude() {
        return cityLongitude;
    }

    public void setCityLongitude(double cityLongitude) {
        this.cityLongitude = cityLongitude;
    }
}
