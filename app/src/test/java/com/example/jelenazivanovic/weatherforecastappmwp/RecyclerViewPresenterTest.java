package com.example.jelenazivanovic.weatherforecastappmwp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.DataFromInternet;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model.RecyclerViewModel;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model.RecyclerViewModelImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenterImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.RecyclerViewView;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by jelena.zivanovic on 12/28/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecyclerViewPresenterTest {

    private RecyclerViewPresenterImpl classUnderTest;

    @Mock
    private RecyclerViewView view;
    @Mock
    private Context context;
    @Mock
    private List<Weather> mList;
    @Mock
    private RecyclerViewModel model;
    @Mock
    private DataFromInternet data;
    @Mock
    private WeatherObject weatherObject;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new RecyclerViewPresenterImpl(view, context);
        classUnderTest.setModel(model);
    }

    @Test
    public void testInvokePresenter () {
        Weather mWeather = new Weather();
        mList.add(mWeather);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                data.getDataFromInternet("Belgrade");

                return weatherObject;
            }
        }).when(model).getWeatherResults();
        classUnderTest.invokePresenter();
        verify(data).getDataFromInternet("Belgrade");
    }

}
