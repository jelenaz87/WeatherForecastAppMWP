package com.example.jelenazivanovic.weatherforecastappmwp;

import android.content.Context;
import android.content.SharedPreferences;

import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;

import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.model.SettingsFragmentModelImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.presenter.SettingsFragmentPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by jelena.zivanovic on 12/22/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SettingsModelTest {
    private SettingsFragmentModelImpl classUnderTest;
    @Mock
    SettingsFragmentPresenter presenter;
    @Captor
    private ArgumentCaptor<SharedPreferences> sharedPreferencesArgumentCaptor;

    @Captor
    private ArgumentCaptor<android.support.v7.preference.Preference> preferenceArgumentCaptor;
    @Captor
    private ArgumentCaptor<Object> value;
    @Mock
    SharedPreferences sharedPreferences;
    @Mock
    ArrayList<Preference> list;
    @Mock
    Context mContext;
    @Mock
    Preference preference;


    @Before
    public void setUp() throws Exception {
        classUnderTest = new SettingsFragmentModelImpl(presenter);
    }

    @Test
    public void invokePresenterMethod() throws Exception {
        when(list.size()).thenReturn(3);
        when(list.get(anyInt())).thenReturn(preference);
        classUnderTest.sendSharedPreferenceAndPreference(sharedPreferences,list);
     //  verify(presenter).getValueForPreferenceScreen(preferenceArgumentCaptor.capture(),value.capture());
        verify(presenter, atLeast(3)).getValueForPreferenceScreen(preferenceArgumentCaptor.capture(),value.capture());








    }
}
