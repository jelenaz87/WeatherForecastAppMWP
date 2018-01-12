package com.example.jelenazivanovic.weatherforecastappmwp.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


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
   Weather loadAEqualWithAnId(int id);

    @Delete
    void deleteWeather(Weather... weathers);

    @Query("SELECT * FROM weather")
    LiveData<List<Weather>> loadAllLoadData();

    @Query("SELECT * FROM weather")
    Flowable<List<Weather>> getFlowableListOfObject ();

    @Query("SELECT * FROM weather WHERE cityName = :name")
    Weather isTableHasResultForCity(String name);

    @Update
    int update(Weather... weathers);

    @Query("SELECT * FROM weather WHERE isChangedLocation = :isChanged")
    Weather getValueForChangeState (boolean isChanged);

}
