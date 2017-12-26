package com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jelena.zivanovic on 12/4/2017.
 */

public class WeatherMountainView {

        @SerializedName( "cod")
        private int code;
        @SerializedName("city")
         private CityObject cityObject;
        @SerializedName("list")
         private ArrayList<WeatherInfo> list = new ArrayList<>();


        public int getCode() {
                return code;
        }

        public void setCode(int code) {
                this.code = code;
        }
        public CityObject getCityObject() {
                return cityObject;
        }

        public void setCityObject(CityObject cityObject) {
                this.cityObject = cityObject;
        }

        public ArrayList<WeatherInfo> getList() {
                return list;
        }

        public void setList(ArrayList<WeatherInfo> list) {
                this.list = list;
        }
}
