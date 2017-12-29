package com.example.jelenazivanovic.weatherforecastappmwp;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.data.WeatherDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by jelena.zivanovic on 12/29/2017.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private WeatherDatabase database;


    @Before
    public void setUp() throws Exception {
        database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                WeatherDatabase.class)
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGetUser() {
        assertEquals(0,database.weatherDao().loadAllWeatherObject().size());

        Weather mWeather = new Weather(1258,500,10,20,89,56,15,13);
        database.weatherDao().insertWeatherObject(mWeather);
        List<Weather> weatherList = database.weatherDao().loadAllWeatherObject();
        assertThat(weatherList.size(), is(1));

        Weather weatherFromdatabase = weatherList.get(0);
        assertEquals(weatherFromdatabase.getWeatherId(), mWeather.getWeatherId());
        assertEquals(weatherFromdatabase.getHumidity(), mWeather.getHumidity());
    }

}
