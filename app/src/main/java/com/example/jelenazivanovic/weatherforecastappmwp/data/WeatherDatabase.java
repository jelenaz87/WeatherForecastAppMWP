package com.example.jelenazivanovic.weatherforecastappmwp.data;


import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by jelena.zivanovic on 12/25/2017.
 */
@android.arch.persistence.room.Database(entities = {Weather.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao ();

    private static WeatherDatabase weatherDatabaseInstance;

    public static WeatherDatabase getWeatherDatabaseInstance (Context context) {
        if (weatherDatabaseInstance == null) {
            weatherDatabaseInstance =
                    Room.databaseBuilder(context.getApplicationContext(), WeatherDatabase.class, "weather_database").allowMainThreadQueries().build();
        }
        return weatherDatabaseInstance;
    }
}
