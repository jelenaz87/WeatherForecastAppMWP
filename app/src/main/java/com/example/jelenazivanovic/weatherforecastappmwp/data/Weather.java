package com.example.jelenazivanovic.weatherforecastappmwp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by jelena.zivanovic on 12/25/2017.
 */
@Entity (tableName = "weather", indices = {@Index(value = {"dateTimeMillis"}, unique = true)})
public class Weather {

    @PrimaryKey (autoGenerate = true)
    private int id;

    private long dateTimeMillis;
    private int weatherId;
    private double minTemperature;
    private double maxTemperature;
    private double pressure;
    private int humidity;
    private double windSpeed;
    private double windDirection;

    @Ignore
    public Weather () {

    }

    public Weather (long dateTimeMillis, int weatherId, double minTemperature, double maxTemperature, double pressure, int humidity, double windSpeed, double windDirection) {
      this.dateTimeMillis = dateTimeMillis;
      this.weatherId = weatherId;
      this.minTemperature = minTemperature;
      this.maxTemperature = maxTemperature;
      this.pressure = pressure;
      this.humidity = humidity;
      this.windSpeed = windSpeed;
      this.windDirection = windDirection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDateTimeMillis() {
        return dateTimeMillis;
    }

    public void setDateTimeMillis(long dateTimeMillis) {
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
