package com.example.weather;

import java.util.ArrayList;

public class City {
    private int id;
    private String name;
    private ArrayList<HourlyConditions> hourlyConditions;
    private ArrayList<Forecast> forecast;
    private double feels_like;
    private int humidity;
    private CurrentTemp currentTemp;
    private Wind wind;
    private ArrayList<Weather> weather;
    private double precipitation;

    public City(int id, String name, ArrayList<HourlyConditions> hourlyConditions, ArrayList<Forecast> forecast, double feels_like, int humidity, Wind wind, CurrentTemp currentTemp, ArrayList<Weather> weather, double precipitation) {
        this.id = id;
        this.name = name;
        this.hourlyConditions = hourlyConditions;
        this.forecast = forecast;
        this.feels_like = feels_like;
        this.humidity = humidity;
        this.currentTemp = currentTemp;
        this.wind = wind;
        this.weather = weather;
        this.precipitation = precipitation;
    }

    public City(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<HourlyConditions> getHourlyConditions() {
        return hourlyConditions;
    }

    public void setHourlyConditions(ArrayList<HourlyConditions> hourlyConditions) {
        this.hourlyConditions = hourlyConditions;
    }

    public ArrayList<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(ArrayList<Forecast> forecast) {
        this.forecast = forecast;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public CurrentTemp getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(CurrentTemp currentTemp) {
        this.currentTemp = currentTemp;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }
}