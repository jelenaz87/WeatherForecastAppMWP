package com.example.jelenazivanovic.weatherforecastappmwp.firstscreen;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public class WeatherObj {

    public String date,description, highTemp, lowTemp;

    public WeatherObj () {

    }

    public WeatherObj (String date, String description, String highTemp, String lowTemp) {
        this.date = date;
        this.description = description;
        this.lowTemp = lowTemp;
        this.highTemp = highTemp;
    }
}
