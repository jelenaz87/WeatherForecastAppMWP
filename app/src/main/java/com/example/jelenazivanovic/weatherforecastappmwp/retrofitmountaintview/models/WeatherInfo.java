package com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jelena.zivanovic on 12/5/2017.
 */

public class WeatherInfo {

    @SerializedName("temp")
    private TemperatureObject temperatureObject;
    @SerializedName("weather")
    private ArrayList<WeatherDetail> weatherDetail = new ArrayList<>();
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("humidity")
    private int humidity;
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
