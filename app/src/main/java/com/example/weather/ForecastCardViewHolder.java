package com.example.weather;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.ItemForcastCardBinding;

import java.util.ArrayList;

public class ForecastCardViewHolder extends RecyclerView.ViewHolder {

    ItemForcastCardBinding binding;
    private DaysForecastAdapter daysForecastAdapter;

    public ForecastCardViewHolder(@NonNull ItemForcastCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

        binding.forecastCardRecyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(),LinearLayoutManager.VERTICAL,false));
        binding.forecastCardRecyclerView.setNestedScrollingEnabled(false);
    }

    public void bind (ArrayList<Forecast> daysForecastArrayList, Context context){
        if (daysForecastAdapter == null){
            daysForecastAdapter = new DaysForecastAdapter(daysForecastArrayList,context);
            binding.forecastCardRecyclerView.setAdapter(daysForecastAdapter);
        }else {
            daysForecastAdapter.updateList(daysForecastArrayList);
        }
    }
}
