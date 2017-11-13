package com.example.adwitiyasingh.weatherapp;

/**
 * Created by adwitiyasingh on 3/28/17.
 */

public class model {
    public Coord coord;
    public Sys sys;
    public Weather weather;
    public Main main;
    public Wind wind;
    public String name;
    public int visibility;

    public model() {
    }

    public model(Coord coord, Sys sys, Weather weather, Main main, Wind wind, String name, int visibility) {
        this.coord = new Coord(coord.lat, coord.lon);
        this.sys = new Sys(sys.country, sys.sunrise, sys.sunset);
        this.weather = new Weather(weather.id, weather.main, weather.description, weather.icon);
        this.main = new Main(main.humidity, main.pressure, main.temp, main.temp_max, main.temp_min);
        this.wind = new Wind(wind.speed, wind.deg);
        this.name = name;
        this.visibility = visibility;
    }


    public String getName() {
        return name;
    }

    public int getVisibility() {
        return visibility;
    }

    public static class Coord {
        private  Double lat, lon;




        public Coord(Double lat, Double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public Double getLongi() {
            return lon;
        }

        public Double getLati() {
            return lat;
        }
    }

    public static class Sys {
        String country;
        int sunrise, sunset;

        public Sys(String country, int sunrise, int sunset) {
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        public String getCountry() {
            return country;
        }

        public int getSunrise() {
            return sunrise;
        }

        public int getSunset() {
            return sunset;
        }
    }

    public static class Weather {
        private int id;
       private String main, description, icon;

        public Weather(int id, String main, String description, String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }

    public static class Main {
        private   int humidity, pressure;
        private  Double temp, temp_max, temp_min;

        public Main(int humidity, int pressure, Double temp, Double temp_max, Double temp_min) {
            this.humidity = humidity;
            this.pressure = pressure;
            this.temp = temp;
            this.temp_max = temp_max;
            this.temp_min = temp_min;
        }

        public int getHumidity() {
            return humidity;
        }

        public int getPressure() {
            return pressure;
        }

        public Double getTemp() {
            return temp;
        }

        public Double getTemp_max() {
            return temp_max;
        }

        public Double getTemp_min() {
            return temp_min;
        }
    }

    public static class Wind {
        private  Double speed;
        private  Integer deg;

        public Wind(Double speed, Integer deg) {
            this.speed = speed;
            this.deg = deg;
        }

        public Double getSpeed() {
            return speed;
        }

        public Integer getDeg() {
            return deg;
        }
    }

}



