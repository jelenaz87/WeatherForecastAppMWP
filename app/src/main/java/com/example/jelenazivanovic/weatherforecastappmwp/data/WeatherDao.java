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

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


/**
 * Created by jelena.zivanovic on 12/25/2017.
 */
@Dao
public interface WeatherDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertWeatherObject (Weather... weathers);

   @Query("SELECT * FROM weather")
   List<Weather> loadAllWeatherObject();

   @Query("SELECT * FROM weather WHERE id_row = :id AND cityName LIKE :name")
   Weather loadAEqualWithAnId(int id, String name);

    @Delete
    void deleteWeather(Weather... weathers);

    @Query("SELECT * FROM weather")
    LiveData<List<Weather>> loadAllLoadData();

    @Query("SELECT * FROM weather")
    Flowable<List<Weather>> getFlowableListOfObject ();

    @Query("SELECT * FROM weather WHERE cityName LIKE :name AND unit LIKE :unit")
    List<Weather> isTableHasResultForCityAndUnit(String name, String unit);

    @Query("SELECT * FROM weather WHERE cityName LIKE :name")
    List<Weather> isTableHasResultForCity(String name);

    @Update
    void updateDatabase (Weather weather);

    @Query("SELECT * FROM weather WHERE isChangedLocation = :isChanged AND cityName LIKE :name")
    List<Weather> getValueForChangeState (boolean isChanged, String name);

    @Query("SELECT * FROM weather WHERE cityName LIKE :name AND unit LIKE :unit")
    List<Weather> getValueWithDefinedCityAndUnit (String name, String unit);

}
