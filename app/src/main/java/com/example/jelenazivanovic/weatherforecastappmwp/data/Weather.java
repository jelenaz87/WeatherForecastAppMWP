package com.example.jelenazivanovic.weatherforecastappmwp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by jelena.zivanovic on 12/25/2017.
 */
@Entity (tableName = "weather", indices = {@Index(value = {"dateTimeMillis"}, unique = true)})
public class Weather implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private int id;

    private int id_row;
    private String dateTimeMillis;
    private int weatherId;
    private String minTemperature;
    private String maxTemperature;
    private String pressure;
    private String humidity;
    private String windString;

    String description;

    @Ignore
    public Weather () {

    }

    public Weather (int id ,String dateTimeMillis, int weatherId, String minTemperature, String maxTemperature, String pressure, String humidity, String windString,String description) {
      this.dateTimeMillis = dateTimeMillis;
      this.weatherId = weatherId;
      this.minTemperature = minTemperature;
      this.maxTemperature = maxTemperature;
      this.pressure = pressure;
      this.humidity = humidity;
      this.windString = windString;
      this.description = description;
      this.id_row = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
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

    public int getId_row() {
        return id_row;
    }

    public void setId_row(int id_row) {
        this.id_row = id_row;
    }

    public boolean isEmptyWeather (Weather weather) {
        if (weather.description == null) {
            return false;
        }
        return true;

    }
}
