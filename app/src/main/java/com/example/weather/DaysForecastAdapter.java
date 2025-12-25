package com.example.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.ItemDaysForecastBinding;

import java.util.ArrayList;

public class DaysForecastAdapter extends RecyclerView.Adapter<DaysForecastViewHolder> {
    private ArrayList<Forecast> forecastArrayList;
    private Context context;

    public DaysForecastAdapter(ArrayList<Forecast> forecastArrayList, Context context) {
        this.forecastArrayList = forecastArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DaysForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDaysForecastBinding itemDaysForecastBinding = ItemDaysForecastBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new DaysForecastViewHolder(itemDaysForecastBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysForecastViewHolder holder, int position) {
        Forecast forecast = forecastArrayList.get(position);
        holder.itemDaysForecastBinding.day.setText(forecast.getDay());
        holder.itemDaysForecastBinding.minTemp.setText(forecast.getMinTemp());
        holder.itemDaysForecastBinding.maxTemp.setText(forecast.getMaxTemp());
        holder.itemDaysForecastBinding.icon.setImageResource(R.drawable.sun);
    }

    @Override
    public int getItemCount() {
        return forecastArrayList.size();
    }

    public void updateList (ArrayList<Forecast> newList){
        forecastArrayList.clear();
        forecastArrayList.addAll(newList);
        notifyDataSetChanged();
    }
}
