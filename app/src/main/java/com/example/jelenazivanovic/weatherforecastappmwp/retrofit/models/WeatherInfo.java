package com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jelena.zivanovic on 12/5/2017.
 */

public class WeatherInfo {

    @SerializedName("main")
    private TemperatureObject temperatureObject;
    @SerializedName("weather")
    private ArrayList<WeatherDetail> weatherDetail = new ArrayList<>();
    @SerializedName("clouds")
    private Clouds mClouds;
    @SerializedName("wind")
    private Wind windInfo;



    public Wind getWindInfo() {
        return windInfo;
    }

    public Clouds getmClouds() {
        return mClouds;
    }

    @SerializedName("dt_txt")

    private String time;



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }




    public TemperatureObject getTemperatureObject() {
        return temperatureObject;
    }

    public void setTemperatureObject(TemperatureObject temperatureObject) {
        this.temperatureObject = temperatureObject;
    }
    public ArrayList<WeatherDetail> getWeatherDetail() {
        return weatherDetail;
    }

    public void setWeatherDetail(ArrayList<WeatherDetail> weatherDetail) {
        this.weatherDetail = weatherDetail;
    }

}
