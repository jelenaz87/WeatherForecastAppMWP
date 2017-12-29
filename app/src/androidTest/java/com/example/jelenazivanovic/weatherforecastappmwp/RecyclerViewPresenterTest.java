package com.example.jelenazivanovic.weatherforecastappmwp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.DataFromInternet;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model.RecyclerViewModel;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenterImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.RecyclerViewView;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jelena.zivanovic on 12/29/2017.
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewPresenterTest {

    private RecyclerViewPresenterImpl classUnderTest;


    private RecyclerViewView view;

    private List<Weather> mList;

    ArgumentCaptor<List<Weather>> listArgumentCaptor;

    private RecyclerViewModel model;



    @Before
    public void setUp() throws Exception {
        view = mock(RecyclerViewView.class);
        mList = mock(List.class);
        listArgumentCaptor = ArgumentCaptor.forClass((Class) List.class);
        model = mock(RecyclerViewModel.class);
        Context context = InstrumentationRegistry.getTargetContext();
        classUnderTest = new RecyclerViewPresenterImpl(view, context);
        classUnderTest.setModel(model);

    }

    @Test
    public void testInvokePresenter () {
       Weather mWeather = new Weather(12,15,18,30,25,33,25,58);
        when(mList.size()).thenReturn(1);
        when(mList.get(anyInt())).thenReturn(mWeather);
        classUnderTest.invokePresenter();
        verify(model,atLeastOnce()).getWeatherResults();
    }

    @Test
    public void testUpdateWeather() {
        Weather mWeather = new Weather(12,15,18,30,25,33,25,58);
        when(mList.size()).thenReturn(1);
        when(mList.get(anyInt())).thenReturn(mWeather);
        classUnderTest.updateWeather(mList);
        verify(view).lisOfWeather(listArgumentCaptor.capture());
    }

}
