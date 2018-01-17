package com.example.jelenazivanovic.weatherforecastappmwp.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import java.io.Serializable;

/**
 * Created by jelena.zivanovic on 1/12/2018.
 */

public class CityWeatherData implements Serializable {

    private int id_row;
    private String dateTimeMillis;
    private int weatherId;
    private double minTemperature;
    private double maxTemperature;
    private String pressure;
    private String humidity;
    private String windString;
    private String description;

    @Ignore
    public CityWeatherData() {
    }

    public CityWeatherData(int id_row, String dateTimeMillis, int weatherId, double minTemperature, double maxTemperature, String pressure, String humidity, String windString, String description) {
        this.id_row = id_row;
        this.dateTimeMillis = dateTimeMillis;
        this.weatherId = weatherId;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windString = windString;
        this.description = description;
    }

    public int getId_row() {
        return id_row;
    }

    public void setId_row(int id_row) {
        this.id_row = id_row;
    }

    public String getDateTimeMillis() {
        return dateTimeMillis;
    }

    public void setDateTimeMillis(String dateTimeMillis) {
        this.dateTimeMillis = dateTimeMillis;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindString() {
        return windString;
    }

    public void setWindString(String windString) {
        this.windString = windString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
