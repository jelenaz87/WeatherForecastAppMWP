package com.example.jelenazivanovic.weatherforecastappmwp.data;

import android.arch.persistence.room.Database;
import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.WeatherObj;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherMountainView;
import com.example.jelenazivanovic.weatherforecastappmwp.utilities.SunshineDateUtils;
import com.example.jelenazivanovic.weatherforecastappmwp.utilities.SunshineWeatherUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jelena.zivanovic on 12/26/2017.
 */

public class DatabaseInsertWeatherInfo {

    public static List<Long> sInitialized;
    private WeatherDatabase database;
    private Context mContext;
    private ArrayList<Weather> mList;
    private long dateTimeMillis;

    public DatabaseInsertWeatherInfo() {

    }

    public DatabaseInsertWeatherInfo (Context mContext) {
        this.mContext = mContext;
        this.database = WeatherDatabase.getWeatherDatabaseInstance(mContext);
        this.mList = new ArrayList<>();
    }

    public void databaseInsertWeatherInfo(WeatherObject weatherObject) {
       // if ( sInitialized != null) {
            int y = 0;
            long normalizedUtcStartDay = SunshineDateUtils.getNormalizedUtcDateForToday();

            for (int i = 0; i < weatherObject.getList().size(); i++) {

                dateTimeMillis = normalizedUtcStartDay + SunshineDateUtils.DAY_IN_MILLIS * y;
                String dateString = SunshineDateUtils.getFriendlyDateString(mContext, dateTimeMillis, false);
                int weatherId = weatherObject.getList().get(i).getWeatherDetail().get(0).getWeatherId();
                String description = SunshineWeatherUtils.getStringForWeatherCondition(mContext, weatherId);

                double tempMinInFahrenheit = weatherObject.getList().get(i).getTemperatureObject().getMinTemperature();
                double tempMinInCelsius = tempMinInFahrenheit - 273.15;
                String minTemp = SunshineWeatherUtils.formatTemperature(mContext, tempMinInCelsius);

                double tempMaxInFahrenheit = weatherObject.getList().get(i).getTemperatureObject().getMaxTemperature();
                double tempMaxInCelsius = tempMaxInFahrenheit - 273.15;
                String maxTemp = SunshineWeatherUtils.formatTemperature(mContext, tempMaxInCelsius);

                double pressure = weatherObject.getList().get(i).getTemperatureObject().getPressure();
                int humidity = weatherObject.getList().get(i).getTemperatureObject().getHumidity();
                double windSpeed = weatherObject.getList().get(i).getWindInfo().getWindSpeed();
                double windDirection = weatherObject.getList().get(i).getWindInfo().getWindDirection();

                Weather weather = new Weather(dateTimeMillis, weatherId, tempMinInCelsius, tempMaxInCelsius, pressure, humidity, windSpeed, windDirection);
                mList.add(weather);
           //     sInitialized = database.weatherDao().insertWeatherObject(weather);

                y++;
                i = i + 7;
            }
        }



    public List<Weather> readDatabaseWeatherInfo () {
      return database.weatherDao().loadAllWeatherObject();

    }

    public void insertData (WeatherMountainView weatherObject ) {
        if ( sInitialized != null) {
            long normalizedUtcStartDay = SunshineDateUtils.getNormalizedUtcDateForToday();

            for (int i = 0; i < weatherObject.getList().size(); i++) {

                dateTimeMillis = normalizedUtcStartDay + SunshineDateUtils.DAY_IN_MILLIS * i;
                String dateString = SunshineDateUtils.getFriendlyDateString(mContext, dateTimeMillis, false);
                int weatherId = weatherObject.getList().get(i).getWeatherDetail().get(0).getWeatherId();
                String description = SunshineWeatherUtils.getStringForWeatherCondition(mContext, weatherId);

                double tempMinInFahrenheit = weatherObject.getList().get(i).getTemperatureObject().getMinTemperature();
                double tempMinInCelsius = tempMinInFahrenheit - 273.15;
                String minTemp = SunshineWeatherUtils.formatTemperature(mContext, tempMinInCelsius);

                double tempMaxInFahrenheit = weatherObject.getList().get(i).getTemperatureObject().getMaxTemperature();
                double tempMaxInCelsius = tempMaxInFahrenheit - 273.15;
                String maxTemp = SunshineWeatherUtils.formatTemperature(mContext, tempMaxInCelsius);

                double pressure = weatherObject.getList().get(i).getPressure();
                int humidity = weatherObject.getList().get(i).getHumidity();
                double windSpeed = weatherObject.getList().get(i).getWindSpeed();
                double windDirection = weatherObject.getList().get(i).getWindDirection();

                Weather weather = new Weather(dateTimeMillis, weatherId, tempMinInCelsius, tempMaxInCelsius, pressure, humidity, windSpeed, windDirection);
                mList.add(weather);
                sInitialized = database.weatherDao().insertWeatherObject(weather);

            }
        }
    }


}
