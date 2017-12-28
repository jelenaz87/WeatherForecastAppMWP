package com.example.jelenazivanovic.weatherforecastappmwp;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.DataFromInternet;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.IsResponseSuccesfull;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenterImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by jelena.zivanovic on 12/28/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DataFromInternetTest {

    private DataFromInternet classUnderTest;


    @Mock
    private DataFromInternet data;
    @Mock
    private IsResponseSuccesfull succesfull;
    @Mock
    private WeatherObject weatherObject;
    @Captor
    private ArgumentCaptor<WeatherObject> weatherObjectCaptor;

    @Before
    public void setUp() throws Exception {
      classUnderTest = new DataFromInternet(succesfull);

    }

    @Test
    public void testGetDataFromInternet () {

//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//
//              succesfull.getResponse(weatherObjectCaptor.capture());
//                return weatherObjectCaptor.capture();
//            }
//        }).when(data).getDataForBelgrade();
//
//        classUnderTest.getDataFromInternet("Belgrade");
//        verify(data, atLeast(1)).getDataForBelgrade();


    }
}
