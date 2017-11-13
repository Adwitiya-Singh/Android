package com.example.adwitiyasingh.weatherapp;

import java.util.ArrayList;

/**
 * Created by adwitiyasingh on 4/10/17.
 */

public class ForecastModel {
    public ForecastModel() {
        lst = new ArrayList<>();
    }

    City cty;
    ArrayList<List> lst;


    public ForecastModel(City cty, ArrayList<List> lst) {
        this.cty = new City(cty.c, cty.City, cty.country);
        this.lst = lst;
    }

    public static class City {
        private model.Coord c;
        private String City, country;

        public City(model.Coord c, String city, String country) {
            this.c = c;
            City = city;
            this.country = country;
        }

        public model.Coord getC() {
            return c;
        }

        public String getCity() {
            return City;
        }

        public String getCountry() {
            return country;
        }
    }

    public static class List {
        private model.Main mn;
        private model.Weather wtr;
        private model.Wind wnd;
        private String dtTime;

        public List(model.Main mn, model.Weather wtr, model.Wind wnd, String dtTime) {
            this.mn = mn;
            this.wtr = wtr;
            this.wnd = wnd;
            this.dtTime = dtTime;


        }

        public model.Main getMn() {
            return mn;
        }

        public model.Weather getWtr() {
            return wtr;
        }

        public model.Wind getWnd() {
            return wnd;
        }

        public String getDtTime() {
            return dtTime;
        }
    }

}
