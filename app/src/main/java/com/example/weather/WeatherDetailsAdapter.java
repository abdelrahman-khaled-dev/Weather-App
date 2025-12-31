package com.example.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.ItemFeelslikeAndHumidityCardBinding;
import com.example.weather.databinding.ItemForcastCardBinding;
import com.example.weather.databinding.ItemHourlyConditionCardBinding;
import com.example.weather.databinding.ItemWindAndPrecipitationCardBinding;

import java.util.ArrayList;

public class WeatherDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private City selectedCity;
    private final int Type_hourly_conditions = 1;
    private final int Type_forecast = 2;
    private final int Type_wind_and_precipitation = 3;
    private final int Type_Feelslike_and_humidity = 4;

    public WeatherDetailsAdapter(City selectedCity) {
        this.selectedCity = selectedCity;
    }

    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case 0:
                return Type_hourly_conditions;
            case 1:
                return Type_forecast;
            case 2:
                return Type_wind_and_precipitation;
            case 3:
            default:
                return Type_Feelslike_and_humidity;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == Type_hourly_conditions) {
            ItemHourlyConditionCardBinding itemHourlyConditionCardBinding = ItemHourlyConditionCardBinding.inflate(inflater, parent, false);
            return new HourlyConditionViewHolder(itemHourlyConditionCardBinding);
        } else if (viewType == Type_forecast) {
            ItemForcastCardBinding itemForcastCardBinding = ItemForcastCardBinding.inflate(inflater, parent, false);
            return new ForecastCardViewHolder(itemForcastCardBinding);
        } else if (viewType == Type_wind_and_precipitation) {
            ItemWindAndPrecipitationCardBinding itemWindAndPrecipitationCardBinding = ItemWindAndPrecipitationCardBinding.inflate(inflater, parent, false);
            return new WindAndPrecipitationViewHolder(itemWindAndPrecipitationCardBinding);
        } else {
            ItemFeelslikeAndHumidityCardBinding itemFeelslikeAndHumidityCardBinding = ItemFeelslikeAndHumidityCardBinding.inflate(inflater, parent, false);
            return new FeelsLikeAndHumidityViewHolder(itemFeelslikeAndHumidityCardBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HourlyConditionViewHolder){
            ((HourlyConditionViewHolder) holder).bind(selectedCity.getHourlyConditions());
        } else if (holder instanceof ForecastCardViewHolder) {
            ((ForecastCardViewHolder) holder).bind(selectedCity.getForecast());
        } else if (holder instanceof WindAndPrecipitationViewHolder) {
            WindAndPrecipitationViewHolder windAndPrecipitationViewHolder = (WindAndPrecipitationViewHolder) holder;
            windAndPrecipitationViewHolder.itemWindAndPrecipitationCardBinding.precipitation.setText(String.valueOf(selectedCity.getPrecipitation()));
            windAndPrecipitationViewHolder.itemWindAndPrecipitationCardBinding.windDeg.setText(String.valueOf(selectedCity.getWind().getDeg()));
            windAndPrecipitationViewHolder.itemWindAndPrecipitationCardBinding.windSpeed.setText(String.valueOf(selectedCity.getWind().getSpeed()));
        } else if (holder instanceof FeelsLikeAndHumidityViewHolder) {
            FeelsLikeAndHumidityViewHolder feelsLikeAndHumidityViewHolder = (FeelsLikeAndHumidityViewHolder) holder;
            feelsLikeAndHumidityViewHolder.itemFeelslikeAndHumidityCardBinding.feelsLikeDeg.setText(String.valueOf(selectedCity.getFeels_like()));
            feelsLikeAndHumidityViewHolder.itemFeelslikeAndHumidityCardBinding.humidity.setText(String.valueOf(selectedCity.getHumidity()));
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
