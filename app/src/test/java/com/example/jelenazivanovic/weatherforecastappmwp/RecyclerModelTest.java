package com.example.jelenazivanovic.weatherforecastappmwp;

import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.data.DatabaseInsertWeatherInfo;
import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.ApiServiceClient;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.DataFromInternet;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model.RecyclerViewModelImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.IsResponseSuccesfull;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.model.SettingsFragmentModelImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jelena.zivanovic on 12/27/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecyclerModelTest {

    private RecyclerViewModelImpl classUnderTest;

    @Captor
    ArgumentCaptor<WeatherObject> weatherObjectBelgrade;
    @Captor
    ArgumentCaptor<List<Weather>> mList;
    @Mock
    private List <Weather> weatherList;
    @Mock
    public IsResponseSuccesfull isResponseSuccesfull;
    @Mock
    private RecyclerViewPresenter presenter;
    @Mock
    private Context context;
    @Mock
    private DataFromInternet data;
    @Mock
    private DatabaseInsertWeatherInfo weatherInfo;
    @Mock
    private Object weatherObject;
    @Mock
    private WeatherObject weatherForBlegrade;

    @Before
    public void setUp() throws Exception {

       classUnderTest = new RecyclerViewModelImpl(presenter, context);
       classUnderTest.setData(data);
       classUnderTest.setWeatherInfo(weatherInfo);
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




//    @Test
//    public void testReadFromDatabase () {
//        data.getDataFromInternet("Belgrade");
//        when (weatherList.size()).thenReturn(0);
//        weatherInfo.insertData(weatherObject);
//        assertFalse(weatherList.isEmpty());
//    }
//
//    @Test
//    public void testGetListForMountainView () {
//        data.getDataFromInternet("MountainView");
//        when (weatherList.size()).thenReturn(0);
//        weatherInfo.insertData(weatherObject);
//        assertFalse(weatherList.isEmpty());
//    }
//
//    @Test
//    public void testResponseIsSucceful () {
//        data.getDataFromInternet("Belgrade");
//        Call<WeatherObject> objectCall = ApiServiceClient.getResponseFromServiceApiForBelgrade().getWeatherObject();
//        Response<WeatherObject> response = null;
//        try {
//            response = objectCall.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        WeatherObject object = response.body();
//        assertTrue(response.isSuccessful());
//    }
}

