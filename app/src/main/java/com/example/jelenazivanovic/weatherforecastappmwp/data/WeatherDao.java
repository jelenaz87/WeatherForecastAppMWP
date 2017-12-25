package com.example.jelenazivanovic.weatherforecastappmwp.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

/**
 * Created by jelena.zivanovic on 12/25/2017.
 */
@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeatherObject (Weather... weathers);

   @Query("SELECT * FROM weather")
   Weather[] loadAllWeatherObject();

   @Query("SELECT * FROM weather WHERE dateTimeMillis > :date")
   Weather[] loadAllDateOlderThan(long date);

    @Delete
    void deleteWeather(Weather... weathers);

}
