package com.example.weather;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.ItemFeelslikeAndHumidityCardBinding;
import com.example.weather.databinding.ItemForcastCardBinding;
import com.example.weather.databinding.ItemHourlyConditionCardBinding;
import com.example.weather.databinding.ItemSelectedCityBinding;
import com.example.weather.databinding.ItemWindAndPrecipitationCardBinding;

import java.util.ArrayList;

public class CityCardAdapter extends RecyclerView.Adapter<CityCardViewHolder> {

    private ArrayList<City> selectedCities;

    public CityCardAdapter(ArrayList<City> selectedCities){
        this.selectedCities = selectedCities;
    }

    @NonNull
    @Override
    public CityCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSelectedCityBinding itemSelectedCityBinding = ItemSelectedCityBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CityCardViewHolder(itemSelectedCityBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CityCardViewHolder holder, int position) {
        City city = selectedCities.get(position);
        holder.binding.cityName.setText(city.getName());
        holder.binding.description.setText(city.getWeather().get(0).getDescription());
        holder.binding.temp.setText(city.getCurrentTemp().getTemp());
        holder.binding.maxTemp.setText(city.getForecast().get(0).getMaxTemp());
        holder.binding.minTemp.setText(city.getForecast().get(0).getMinTemp());

        holder.binding.getRoot().setOnClickListener(v -> {
        Intent weatherIntent = new Intent(v.getContext(), WeatherActivity.class);
        weatherIntent.putExtra("cityId",city.getId());
        v.getContext().startActivity(weatherIntent);
        });
    }

    @Override
    public int getItemCount() {
        return selectedCities.size();
    }

    public void addCity(City city){
        for (City c : selectedCities){
            if (c.getId() == city.getId()){
                return;
            }
        }
        selectedCities.add(city);
        notifyItemInserted(selectedCities.size()-1);
    }

    public void updateWithNewCities(ArrayList<City> cities){
        this.selectedCities.clear();
        this.selectedCities.addAll(cities);
        notifyDataSetChanged();
    }

}