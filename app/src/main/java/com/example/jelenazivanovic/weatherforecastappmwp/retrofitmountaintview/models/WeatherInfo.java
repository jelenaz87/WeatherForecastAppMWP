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
    private float pressure;
    @SerializedName("humidity")
    private float humidity;
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

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
