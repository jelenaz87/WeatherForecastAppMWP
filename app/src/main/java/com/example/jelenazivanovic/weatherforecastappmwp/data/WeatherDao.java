package com.example.jelenazivanovic.weatherforecastappmwp.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by jelena.zivanovic on 12/25/2017.
 */
@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertWeatherObject (Weather... weathers);

   @Query("SELECT * FROM weather")
   List<Weather> loadAllWeatherObject();

   @Query("SELECT * FROM weather WHERE id_row = :id")
   Weather loadAEqualThanId(int id);

    @Delete
    void deleteWeather(Weather... weathers);

    @Query("SELECT * FROM weather")
    LiveData<List<Weather>> loadAllLoadData();


}
