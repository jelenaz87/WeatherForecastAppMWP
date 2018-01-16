package com.example.jelenazivanovic.weatherforecastappmwp.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by jelena.zivanovic on 12/25/2017.
 */
@Entity (tableName = "weather")
public class Weather implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @Ignore
    private String cityCode;


    private String cityName;

    @Embedded
    private CityWeatherData cityObject;

    private boolean isChangedLocation;

    private String unit;



    public Weather () {

    }

    @Ignore
    public Weather(String cityName, CityWeatherData data ){
        this.cityName = cityName;
        this.cityObject = data;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public CityWeatherData getCityObject() {
        return cityObject;
    }

    public void setCityObject(CityWeatherData cityObject) {
        this.cityObject = cityObject;
    }

    public boolean isChangedLocation() {
        return isChangedLocation;
    }

    public void setChangedLocation(boolean changedLocation) {
        isChangedLocation = changedLocation;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isEmptyWeather (Weather weather) {
        if (weather.cityName == null) {
            return false;
        }
        return true;

    }


}
