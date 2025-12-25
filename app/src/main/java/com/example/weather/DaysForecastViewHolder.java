package com.example.weather;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.ItemDaysForecastBinding;

import java.util.ArrayList;

public class DaysForecastViewHolder extends RecyclerView.ViewHolder {
    ItemDaysForecastBinding itemDaysForecastBinding;

    public DaysForecastViewHolder(@NonNull ItemDaysForecastBinding itemDaysForecastBinding) {
        super(itemDaysForecastBinding.getRoot());
        this.itemDaysForecastBinding = itemDaysForecastBinding ;
    }
}
