package com.example.weather;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class JsonClass {
    public static ArrayList<City> parseCitiesFromAssets(Context context) {
        ArrayList<City> citiesList = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open("cities.json");
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            inputStream.close();

            String json = new String(bytes, "UTF-8");
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("cities");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject cityObj = jsonArray.getJSONObject(i);
                int id = cityObj.getInt("id");
                String name = cityObj.getString("name");
                double feelsLike = cityObj.getDouble("feels_like");
                int humidity = cityObj.getInt("humidity");
                double precipitation = cityObj.optDouble("precipitation");

                ArrayList<HourlyConditions> conditionsArrayList = new ArrayList<>();
                JSONArray hourlyConditionArray = cityObj.getJSONArray("hourlyConditions");
                for (int y = 0; y<hourlyConditionArray.length(); y++) {
                    JSONObject hourlyConditionObject = hourlyConditionArray.getJSONObject(y);
                    String hour = hourlyConditionObject.getString("hour");
                    String temp = hourlyConditionObject.getString("temp");
                    conditionsArrayList.add(new HourlyConditions(hour,temp));
                }

                ArrayList<Forecast> forecastList = new ArrayList<>();
                JSONArray forecastArray = cityObj.getJSONArray("forecast");
                for (int f = 0; f <forecastArray.length(); f++) {
                    JSONObject forecastObj = forecastArray.getJSONObject(f);
                    String forecastDay = forecastObj.getString("day");
                    String forecastMinTemp = forecastObj.getString("minTemp");
                    String forecastMaxTemp = forecastObj.getString("maxTemp");
                    forecastList.add(new Forecast(forecastDay,forecastMinTemp,forecastMaxTemp));
                }

                JSONObject windObj = cityObj.getJSONObject("wind");
                double windSpeed = windObj.getDouble("speed");
                int windDeg = windObj.getInt("deg");
                Wind wind = new Wind(windSpeed, windDeg);

                String temp = cityObj.getString("currentTemp");
                CurrentTemp currentTemp = new CurrentTemp(temp);



                ArrayList<Weather> weatherList = new ArrayList<>();
                JSONArray weatherArray = cityObj.getJSONArray("weather");
                for (int y = 0; y < weatherArray.length(); y++) {
                    JSONObject weatherObj = weatherArray.getJSONObject(y);
                    String weatherDescription = weatherObj.getString("description");
                    weatherList.add(new Weather(weatherDescription));
                }
                City city = new City(id, name, conditionsArrayList, forecastList, feelsLike, humidity, wind, currentTemp, weatherList, precipitation);
                citiesList.add(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return citiesList;
    }
}
