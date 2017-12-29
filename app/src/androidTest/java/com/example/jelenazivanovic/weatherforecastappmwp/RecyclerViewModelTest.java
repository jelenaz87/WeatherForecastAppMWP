package com.example.jelenazivanovic.weatherforecastappmwp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.jelenazivanovic.weatherforecastappmwp.data.DatabaseInsertWeatherInfo;
import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.DataFromInternet;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model.RecyclerViewModelImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.IsResponseSuccesfull;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.MainActivity;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by jelena.zivanovic on 12/29/2017.
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewModelTest {

    private RecyclerViewModelImpl classUnderTest;
    private RecyclerViewPresenter presenter;
    private ArgumentCaptor<List<Weather>> mList ;
    private DatabaseInsertWeatherInfo weatherInfo;
    private List<Weather> weatherList;
    private Object weatherObject;
    private DataFromInternet data;
   private IsResponseSuccesfull isResponseSuccesfull;
    private ArgumentCaptor<WeatherObject> weatherObjectBelgrade;

    @Before
    public void setUp() throws Exception {
        presenter = mock(RecyclerViewPresenter.class);
        weatherList = mock(List.class);
        weatherObject = mock(Object.class);
        data = mock(DataFromInternet.class);
        weatherInfo = mock(DatabaseInsertWeatherInfo.class);
        mList = ArgumentCaptor.forClass((Class)List.class);
        isResponseSuccesfull = mock(IsResponseSuccesfull.class);
        weatherObjectBelgrade = ArgumentCaptor.forClass(WeatherObject.class);
        Context appContext = InstrumentationRegistry.getTargetContext();
        classUnderTest = new RecyclerViewModelImpl(presenter,appContext);
        classUnderTest.setData(data);
        classUnderTest.setWeatherInfo(weatherInfo);

    }

    @Test
    public void testGetResponse () throws Exception{
        Weather weather = new Weather(12,500,10,15,100,45,30,25);
        when(weatherList.get(anyInt())).thenReturn(weather);
        when(weatherList.size()).thenReturn(1);
        when(weatherInfo.readDatabaseWeatherInfo()).thenReturn(weatherList);
        classUnderTest.getResponse(weatherObject);
        verify(presenter).updateWeather(mList.capture());
        assertEquals(weatherList,mList.getValue());
    }
    @Test
    public void testGetWeatherResults () throws Exception {

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                isResponseSuccesfull.getResponse(weatherObject);
                return weatherObject;
            }
        }).when(data).getDataFromInternet("Belgrade");

        classUnderTest.getWeatherResults();

        verify(isResponseSuccesfull).getResponse(weatherObjectBelgrade.capture());
    }

}
