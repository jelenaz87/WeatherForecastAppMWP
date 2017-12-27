package com.example.jelenazivanovic.weatherforecastappmwp;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model.RecyclerViewModelImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.model.SettingsFragmentModelImpl;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by jelena.zivanovic on 12/27/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecyclerModelTest {

    private RecyclerViewModelImpl classUnderTest;

    @Mock
    private RecyclerViewPresenter presenter;

    @Before
    public void setUp() throws Exception {
       classUnderTest = new RecyclerViewModelImpl(presenter);
    }



}
